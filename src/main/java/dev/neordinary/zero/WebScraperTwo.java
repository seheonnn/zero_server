package dev.neordinary.zero;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraperTwo {

    public static void main(String[] args) {
        String url = "https://www.fatsecret.kr/칼로리-영양소"+"/search?q=2%ED%94%84%EB%A1%9C";

        try {
            Document doc = Jsoup.connect(url).get();
            List<String[]> productList = new ArrayList<>();

            Elements productLinks = doc.select("a.prominent");

            for (Element link : productLinks) {
                String productName = link.text().trim();
                String productUrl = "https://www.fatsecret.kr" + link.attr("href");
                String productSugar = "";
                String productKcal = "";
                String productSize = "";
                try {
                    Document infoDoc = Jsoup.connect(productUrl).get();
                    Elements body = infoDoc.getAllElements();
                    System.out.println(body.text());
                    Elements sugarElements = infoDoc.getElementsMatchingOwnText("설탕당");
                    if (!sugarElements.isEmpty()) {
                        Element sugarElement = sugarElements.first();
                        Element nextElement = sugarElement.nextElementSibling();
                        if (nextElement != null) {
                            productSugar = nextElement.text();
                        } else {
                            System.out.println("설탕당 값이 없습니다.");
                        }
                    } else {
                        System.out.println("설탕당을 찾을 수 없습니다.");
                    }

                    Elements kcalElements = infoDoc.getElementsMatchingOwnText("열량");
                    if (!kcalElements.isEmpty()) {
                        Element kcalElement = kcalElements.first();
                        Element nextElement = kcalElement.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling();
                        if (nextElement != null) {
                            productKcal = nextElement.text();
                        } else {
                            System.out.println("칼로리 값이 없습니다.");
                        }
                    } else {
                        System.out.println("칼로리를 찾을 수 없습니다.");
                    }

                    String sizeElement = infoDoc.getElementsContainingOwnText("ml").toString();
                    // 정규 표현식을 사용하여 괄호 안의 숫자 추출
                    Pattern pattern = Pattern.compile("(\\d+) ml");
                    Matcher matcher = pattern.matcher(sizeElement);

                    // 정규 표현식과 일치하는 부분이 있는지 확인하고 숫자 추출
                    if (matcher.find()) {
                        productSize = matcher.group(1);
                    } else {
                        System.out.println("용량이 없습니다.");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                productList.add(new String[]{productName, productSugar, productKcal, productSize});
            }

            for (String[] product : productList) {
                System.out.println("제품 이름: " + product[0] + ", 당: " + product[1] + ", 칼로리: " + product[2] + ", 용량: " + product[3]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
