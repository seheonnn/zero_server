package dev.neordinary.zero.service;

import dev.neordinary.zero.base.BaseException;
import dev.neordinary.zero.base.BaseResponseStatus;
import dev.neordinary.zero.domain.Product;
import dev.neordinary.zero.domain.ProductRedisRepository;
import dev.neordinary.zero.dto.ProductInfo;
import dev.neordinary.zero.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRedisRepository productRedisRepository;

    private static final String MAIN_URL = "https://www.fatsecret.kr";
    private static final String PRODUCT_LIST_URL = MAIN_URL + "/칼로리-영양소/search?q=";
    private static final String PAGE = "&pg=";
    private static final int MAX_PRODUCT_PER_PAGE = 10;
    private static final int MAX_PRODUCT_PER_VIEW = 5;

    public ProductResponse getProductResponse(String keyword, Integer lastProductId) {
        Optional<Product> product = findProductList(keyword);
        if (product.isPresent()) {
            List<ProductInfo> productInfoList = product.get().getProductInfoList();
            if (lastProductId + 1 >= productInfoList.size()) {
                throw new BaseException(BaseResponseStatus.PRODUCT_INDEX_OUT_OF_RANGE);
            }
            if (lastProductId + MAX_PRODUCT_PER_VIEW >= productInfoList.size())
                return ProductResponse.builder()
                        .productInfoList(productInfoList.subList(lastProductId + 1, productInfoList.size()))
                        .lastProductId(productInfoList.size() - 1)
                        .build();
            return ProductResponse.builder()
                    .productInfoList(productInfoList.subList(lastProductId + 1, lastProductId + MAX_PRODUCT_PER_VIEW + 1))
                    .lastProductId(lastProductId + MAX_PRODUCT_PER_VIEW)
                    .build();
        }

        int pageId = 0;
        String url = PRODUCT_LIST_URL + keyword + PAGE + pageId;
        int productTotal = getProductTotal(url);
        int pageTotal = productTotal % MAX_PRODUCT_PER_PAGE == 0 ? productTotal / MAX_PRODUCT_PER_PAGE : productTotal / MAX_PRODUCT_PER_PAGE + 1;
        List<ProductInfo> productList = new ArrayList<>();

        while (pageId < pageTotal) {
            url = PRODUCT_LIST_URL + keyword + PAGE + pageId;

            try {
                Document doc = Jsoup.connect(url).get();
                Elements productLinks = doc.select("a.prominent");

                for (Element link : productLinks) {
                    String productName = link.text().trim().replaceAll("\\(\\w+\\)", "").strip();
                    String productUrl = MAIN_URL + link.attr("href");
                    String productSugar = "";
                    String productKcal = "";
                    String productSize = "";

                    try {
                        Document infoDoc = Jsoup.connect(productUrl).get();
                        Elements body = infoDoc.getAllElements();
                        System.out.println(body.text());
                        productName = body.text().split(" ")[0] + " " + productName;
                        productSugar = getProductSugar(infoDoc);
                        productKcal = getProductKcal(infoDoc);
                        productSize = getProductSize(infoDoc);
                    } catch (IOException e) {
                        throw new BaseException(BaseResponseStatus.CRAWLING_SERVER_ERROR);
                    }

                    if (productSugar != null && productKcal != null && productSize != null)
                        productList.add(ProductInfo.builder()
                                .productName(productName)
                                .productSugar(Double.valueOf(productSugar))
                                .productKcal(Integer.valueOf(productKcal))
                                .productSize(Integer.valueOf(productSize))
                                .build());
                }
            } catch (IOException e) {
                throw new BaseException(BaseResponseStatus.CRAWLING_SERVER_ERROR);
            }

            pageId++;
        }
        addProductList(keyword, productList);
        return ProductResponse.builder()
                .productInfoList(productList.subList(0, MAX_PRODUCT_PER_VIEW))
                .lastProductId(MAX_PRODUCT_PER_VIEW - 1)
                .build();
    }

    @Transactional
    public void addProductList(String keyword, List<ProductInfo> productInfoList) {
        productRedisRepository.save(Product.createProduct(keyword, productInfoList));
    }

    @Transactional
    public Optional<Product> findProductList(String keyword) {
        return productRedisRepository.findById(keyword);
    }

    private int getProductTotal(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return Integer.parseInt(doc.select("div.searchResultSummary").text().split("중")[0]);
        } catch (IOException e) {
            throw new BaseException(BaseResponseStatus.CRAWLING_SERVER_ERROR);
        }
    }

    private String getProductSugar(Document infoDoc) {
        Elements sugarElements = infoDoc.getElementsMatchingOwnText("설탕당");

        if (!sugarElements.isEmpty()) {
            Element sugarElement = sugarElements.first();
            Element nextElement = sugarElement.nextElementSibling();
            if (nextElement != null) {
                return nextElement.text().replaceAll("g", "");
            }
            return null;
        }
        return null;
    }

    private String getProductKcal(Document infoDoc) {
        Elements kcalElements = infoDoc.getElementsMatchingOwnText("열량");

        if (!kcalElements.isEmpty()) {
            Element kcalElement = kcalElements.first();
            Element nextElement = kcalElement.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling();
            if (nextElement != null) {
                return nextElement.text().replaceAll(" kcal", "");
            }
            return null;
        }
        return null;
    }

    private String getProductSize(Document infoDoc) {
        String sizeElement = infoDoc.getElementsContainingOwnText("ml").toString();

        Pattern pattern = Pattern.compile("(\\d+) ml");
        Matcher matcher = pattern.matcher(sizeElement);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
