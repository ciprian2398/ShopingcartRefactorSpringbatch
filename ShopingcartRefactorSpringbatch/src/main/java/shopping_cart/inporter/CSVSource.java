package shopping_cart.inporter;

import au.com.bytecode.opencsv.CSVReader;
import shopping_cart.product.Product;
import shopping_cart.product.caster.ProductParser;
import shopping_cart.product.caster.SimpleProductParser;

import java.io.FileReader;
import java.io.IOException;

class CSVSource {
    private final String csvFile;
    private CSVReader csvReader;
    private String[] columnsNmaes;
    private ProductParser productCaster;

    public CSVSource(String csvFile) {
        this.csvFile = csvFile;
        init();
    }

    public Product nextProduct() throws IOException {
        String[] nextLine = csvReader.readNext();
        if (isExpectedFile() && isValidProductTemplate(nextLine))
            return productCaster.castToProduct(nextLine[0], nextLine[1], nextLine[2]);
        return null;
    }

    private boolean isValidProductTemplate(String[] line) {
        return (line != null && line.length == 3);
    }

    private boolean isExpectedFile() {
        return columnsNmaes[0].equals("NAME") &&
                columnsNmaes[1].equals("PRICE") &&
                columnsNmaes[2].equals("QUANTITY");
    }

    private void init() {
        try {
            csvReader = new CSVReader(new FileReader(csvFile), ';');
            columnsNmaes = csvReader.readNext();
        } catch (IOException e) {
            System.out.println("IO Exception.");
        }
        productCaster = new SimpleProductParser();
    }
}