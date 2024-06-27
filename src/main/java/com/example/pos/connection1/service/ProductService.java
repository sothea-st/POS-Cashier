package com.example.pos.connection1.service;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.constant.JavaValidation;
import com.example.pos.connection1.entity.FileStore;
import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.entity.ImportDetail;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.models.ProductModel;
import com.example.pos.connection1.entity.models.testexcel.TestFileImportExcel;
import com.example.pos.connection1.repository.FileStoreRepository;
import com.example.pos.connection1.repository.ImportDetailRepository;
import com.example.pos.connection1.repository.ProductRepository;
import com.example.pos.connection1.repository.productProjection.ProductProjection;
import com.example.pos.connection1.util.exception.customeException.JavaNotFoundByIdGiven;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private FileStoreRepository fileStore;
    @Autowired
    private HttpSession session;

    @Autowired
    private ImportDetailRepository repoImp;

    @Autowired
    private ImportService service;

    public void importFileExcel(MultipartFile multipartFile) throws IOException {
        // Load Excel file
        InputStream inputStream = multipartFile.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);

        // Get the first sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Iterate through each row
        Iterator<Row> iterator = sheet.iterator();
        List<TestFileImportExcel> entities = new ArrayList<>();

        while (iterator.hasNext()) {
            Row currentRow = iterator.next();

            // Skip header row
            if (currentRow.getRowNum() == 0) {
            continue;
            }

            // Read data from each column
            Iterator<Cell> cellIterator = currentRow.iterator();
            TestFileImportExcel t = new TestFileImportExcel();

            int cellIndex = 0;
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
             
                switch (currentCell.getCellType()) {
                    case STRING:
                        if (cellIndex == 0) {
                            System.out.println("Column 0 : " + currentCell.getStringCellValue());
                            t.setItemName(currentCell.getStringCellValue());
                        } else if (cellIndex == 1) {
                            System.out.println("Column 1 : " + currentCell.getStringCellValue());
                            t.setUnit(currentCell.getStringCellValue());
                        }  
                        break;
                    case NUMERIC:

                        double numericValue = currentCell.getNumericCellValue();
                        String cformattedValue = String.format("%.2f", numericValue); // Format as needed

                        if (cellIndex == 2) {
                            t.setQty(numericValue);
                        } else if (cellIndex == 3) {
                            t.setRate(BigDecimal.valueOf(Double.parseDouble(cformattedValue)));
                        }  

                        t.setValue(null);

                    
                        break;
                    // Handle other cell types if necessary
                    default:
                        break;
                }

                cellIndex++;
            }

            entities.add(t);
        }


        entities.forEach(e->{
            double value = e.getQty() * e.getRate().doubleValue();
            System.out.println(e.getItemName() +" "+ e.getUnit() +" "+ e.getQty() +" " + e.getRate() +" " +value);
        });

        workbook.close();
    }

    public Product addProduct(Product p, MultipartFile file, MultipartFile flagFile) throws IOException {

        // boolean proNameKh = repo.existsByProNameKh(p.getProNameKh());
        // JavaValidation.checkDataAlreadyExists(proNameKh);

        // boolean proNameEn = repo.existsByProNameEn(p.getProNameEn());
        // JavaValidation.checkDataAlreadyExists(proNameEn);

        // Object idUser = session.getAttribute(JavaConstant.userId);

        Product pro = new Product();
        pro.setCatId(p.getCatId());
        // pro.setUnitTypeId(p.getUnitTypeId());
        pro.setProNameKh(p.getProNameKh());
        pro.setProNameEn(p.getProNameEn());
        pro.setCost(p.getCost());
        pro.setPrice(p.getPrice());
        // pro.setCostKhr(p.getCostKhr());
        // pro.setPriceKhr(p.getPriceKhr());
        pro.setNote(p.getNote());
        pro.setTaxId(p.getTaxId());
        pro.setCreateBy(p.getCreateBy());
        pro.setWeight(p.getWeight());
        pro.setBarcode(p.getBarcode());
        if (p.getDiscount() == null) {
            pro.setDiscount(BigDecimal.valueOf(0));
        } else {
            pro.setDiscount(p.getDiscount());
        }

        pro.setBrandId(p.getBrandId());

        /*
         * proQty just use to check codition with import
         * case user add qty import will be working
         * case user not add qty import not working
         * column name pro_qty in table pos_product will have value 0 fixes
         */

        pro.setProQty(0);

        // pro.setDiscountPercentag(p.getDiscountPercentag().isEmpty() ? "0" :
        // p.getDiscountPercentag());
        pro.setProductStatus(p.getProductStatus()); // for detail product in or out stock
        if (file == null || file.isEmpty()) {
            pro.setProImageName(JavaConstant.defaultNameImage);
        } else {
            // JavaStorage.storeImage(file); for save image to path assests/product in
            // project
            // String fileName = JavaStorage.setFileName(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            fileName = fileName.replace(" ", "-");

            // save information image to table pos_file
            FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
            fileStore.save(f);
            pro.setProImageName(fileName);
        }

        if (flagFile == null || flagFile.isEmpty()) {
            pro.setFlag(JavaConstant.defaultFlagNameImage);
        } else {
            // String flagName = JavaStorage.setFileName(flagFile.getOriginalFilename());
            String flagName = flagFile.getOriginalFilename();
            flagName = flagName.replace(" ", "-");
            pro.setFlag(flagName);
            FileStore f = new FileStore(flagName, flagName, flagFile.getContentType(), flagFile.getBytes());
            fileStore.save(f);
        }

        repo.save(pro);

        /*
         * when user add new product it will auto import
         */

        if (p.getProQty() != null) {
            Import import1 = new Import();
            import1.setCreateBy(0);
            import1.setEmpId(0);
            import1.setSubId(0);
            import1.setImpDate(JavaConstant.currentDate);
            import1.setDiscount(BigDecimal.valueOf(0));
            import1.setTotal(BigDecimal.valueOf(p.getProQty() * p.getCost().doubleValue()));

            List<ImportDetail> listDetail = new ArrayList<>();
            ImportDetail importDetail = new ImportDetail();
            importDetail.setProductId(pro.getId());
            importDetail.setQtyNew(p.getProQty());
            importDetail.setCost(p.getCost());
            importDetail.setAmount(BigDecimal.valueOf(p.getCost().doubleValue() * p.getProQty()));
            importDetail.setExpireDate("");
            listDetail.add(importDetail);
            import1.setDetails(listDetail);
            service.addImport(import1);
        }

        return pro;
    }

    public Product readData(int id) {
        Integer getOldQty = repo.getOldQty(id);

        Product data = repo.getProductById(id);
        data.setProQty(getOldQty == null ? 0 : getOldQty);
        if (data == null)
            throw new JavaNotFoundByIdGiven();
        return data;
    }

    public byte[] getImage(String imageName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("assets\\product\\" + imageName)); // convert imageName to byte[]
        String base64 = Base64.getEncoder().encodeToString(bytes); // encode byte[] to string base64
        return Base64.getDecoder().decode(base64); // decode string base64
    }

    public List<ProductModel> getProduct(int limit, int perPage, int page) {

        List<ProductModel> list = new ArrayList<>();

        if (limit == 0) {
            List<ProductProjection> allPro = repo.getAllProduct(perPage, page);
            for (int i = 0; i < allPro.size(); i++) {
                var data = allPro.get(i);

                Integer qty = repoImp.getQty(data.getId());
                if (qty == null)
                    qty = 0;
                ProductModel p = proModel(data, qty);
                list.add(p);
            }
            return list;
        }

        List<ProductProjection> lPro = repo.getProduct(limit);
        for (int i = 0; i < lPro.size(); i++) {
            var data = lPro.get(i);
            Integer qty = repoImp.getQty(data.getId());
            if (qty == null)
                qty = 0;
            ProductModel p = proModel(data, qty);
            list.add(p);
        }

        return list;
    }

    public Product editProduct(int id, Product editProduct, MultipartFile file, MultipartFile flag) throws IOException {
        Product previousPro = repo.findById(id).get();
        String fileName = previousPro.getProImageName();
        String flagName = previousPro.getFlag();

        // if (!Objects.equals(previousPro.getProNameKh(), editProduct.getProNameKh()))
        // {
        // boolean isExist = repo.existsByProNameKh(editProduct.getProNameKh());
        // JavaValidation.checkDataAlreadyExists(isExist);
        // }

        // if (!Objects.equals(previousPro.getProNameEn(), editProduct.getProNameEn()))
        // {
        // boolean isExist = repo.existsByProNameEn(editProduct.getProNameEn());
        // JavaValidation.checkDataAlreadyExists(isExist);
        // }

        Object idUser = session.getAttribute(JavaConstant.userId);

        if (Objects.equals(fileName, JavaConstant.defaultNameImage))
            fileName = "";
        if (file != null && !file.isEmpty()) {
            // save information image to table pos_file
            // String imgName = JavaStorage.setFileName(file.getOriginalFilename());
            String imgName = file.getOriginalFilename();
            FileStore f1 = new FileStore(imgName, imgName, file.getContentType(), file.getBytes());
            fileStore.save(f1);
            previousPro.setProImageName(imgName);
        }

        if (Objects.equals(flagName, JavaConstant.defaultFlagNameImage))
            flagName = "";

        if (flag != null && !flag.isEmpty()) {
            // String fName = JavaStorage.setFileName(flag.getOriginalFilename());
            String fName = flag.getOriginalFilename();
            FileStore f2 = new FileStore(fName, fName, flag.getContentType(), flag.getBytes());
            fileStore.save(f2);
            previousPro.setFlag(fName);
        }

        previousPro.setProNameKh(editProduct.getProNameKh());
        previousPro.setProNameEn(editProduct.getProNameEn());
        previousPro.setTaxId(editProduct.getTaxId());
        previousPro.setProductStatus(editProduct.getProductStatus());

        // previousPro.setCostKhr(editProduct.getCostKhr());
        previousPro.setCost(editProduct.getCost());
        // previousPro.setPriceKhr(editProduct.getPriceKhr());
        previousPro.setPrice(editProduct.getPrice());
        previousPro.setWeight(editProduct.getWeight());
        previousPro.setBarcode(editProduct.getBarcode());
        previousPro.setDiscount(editProduct.getDiscount());
        previousPro.setBrandId(editProduct.getBrandId());
        // previousPro.setProductStatus(editProduct.getProductStatus()); // for detail
        // product in or out stock
        // previousPro.setUnitTypeId(editProduct.getUnitTypeId());
        previousPro.setCatId(editProduct.getCatId());
        previousPro.setNote(editProduct.getNote());
        // previousPro.setDiscountPercentag(editProduct.getDiscountPercentag());
        // previousPro.setCreateBy((Integer) idUser);
        repo.save(previousPro);

        /*
         * when user add new product it will auto import
         */

        if (editProduct.getProQty() != null) {
            Import import1 = new Import();
            import1.setCreateBy(0);
            import1.setEmpId(0);
            import1.setSubId(0);
            import1.setImpDate(JavaConstant.currentDate);
            import1.setDiscount(BigDecimal.valueOf(0));
            import1.setTotal(BigDecimal.valueOf(editProduct.getProQty() * editProduct.getCost().doubleValue()));

            List<ImportDetail> listDetail = new ArrayList<>();
            ImportDetail importDetail = new ImportDetail();
            importDetail.setProductId(id);
            importDetail.setQtyNew(editProduct.getProQty());
            importDetail.setCost(editProduct.getCost());
            importDetail.setAmount(BigDecimal.valueOf(editProduct.getCost().doubleValue() * editProduct.getProQty()));
            importDetail.setExpireDate("");
            listDetail.add(importDetail);
            import1.setDetails(listDetail);
            service.addImport(import1);
        }

        return previousPro;
    }

    public void deleteProduct(int id, Product p) {
        Optional<Product> data = repo.findById(id);
        Product obj = data.get();
        obj.setStatus(p.isStatus());
        obj.setDeleted(p.isDeleted());
        repo.save(obj);
    }

    public byte[] getFile(String id) throws IOException {
        Optional<FileStore> fileDB = fileStore.findById(id);
        return fileDB.get().getData();
    }

    public List<ProductModel> getProductByCatId(int catId, int limit, int page) {

        List<ProductProjection> listData = repo.getProductByCatId(catId, limit, page);

        List<ProductModel> list = new ArrayList<>();

        for (int i = 0; i < listData.size(); i++) {
            var data = listData.get(i);
            Integer qty = repoImp.getQty(data.getId());
            if (qty == null)
                qty = 0;
            ProductModel p = proModel(data, qty);
            list.add(p);
        }
        return list;

        // for (int i = 0; i < listData.size(); i++) {

        // if (i >= limit - 20) {
        // var data = listData.get(i);
        // Integer qty = repoImp.getQty(data.getId());
        // if (qty == null)
        // qty = 0;
        // ProductModel p = proModel(data, qty);
        // list.add(p);
        // }
        // }
        // return list;
    }

    public int count(int catId) {
        return repo.countProduct(catId);
    }

    public int countProductByBrandId(int brandId) {
        return repo.countProductByBrandId(brandId);
    }

    public List<ProductModel> getProductPromotion() {
        List<ProductProjection> listD = repo.getProductPromotion();
        List<ProductModel> listModel = new ArrayList<>();
        for (int i = 0; i < listD.size(); i++) {
            var data = listD.get(i);
            Integer qty = repoImp.getQty(data.getId());
            if (qty == null)
                qty = 0;
            ProductModel p = proModel(data, qty);
            listModel.add(p);
        }
        return listModel;
    }

    public List<ProductModel> getListProductByBrandId(int brandId, int limit, int page) {

        List<ProductProjection> listD = repo.getProductByBrandId(brandId, limit, page);

        List<ProductModel> listModel = new ArrayList<>();
        for (int i = 0; i < listD.size(); i++) {
            var data = listD.get(i);
            Integer qty = repoImp.getQty(data.getId());
            if (qty == null)
                qty = 0;
            ProductModel p = proModel(data, qty);
            listModel.add(p);
        }
        return listModel;
    }

    public ProductModel proModel(ProductProjection data, int qty) {
        ProductModel p = new ProductModel(
                data.getBrand_id(),
                data.getPro_name_kh(),
                data.getPro_image_name(),
                data.getProduct_status(),
                data.getPro_name_en(),
                data.getId(),
                data.getFlag(),
                data.getDiscount(),
                data.getCost(),
                data.getPrice(),
                data.getWeight(),
                data.getBarcode(),
                data.getCat_id(),
                data.getCode_expired(),
                data.getCode_out_stock(),
                qty);
        return p;
    }

    public Product updateDiscount(int id, BigDecimal discount) {
        Optional<Product> p = repo.getProductByOptionalId(id);
        Product data = p.get();
        data.setDiscount(discount);
        repo.save(data);
        return data;
    }

    public List<ProductModel> getNewProduct(int limit, int page, int number) {
        List<ProductModel> list = new ArrayList<>();
        List<ProductProjection> listData = repo.getNewProduct(number);

        for (int i = page; i < listData.size(); i++) {
            if (i == limit)
                break;
            var data = listData.get(i);
            Integer qty = repoImp.getQty(data.getId());
            if (qty == null)
                qty = 0;
            ProductModel p = proModel(data, qty);
            list.add(p);
        }

        return list;
    }

}
