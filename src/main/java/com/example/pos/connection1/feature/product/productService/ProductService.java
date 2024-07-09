package com.example.pos.connection1.feature.product.productService;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.entity.FileStore;
import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.entity.ImportDetail;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.models.ProductModel;
import com.example.pos.connection1.feature.attribute.AttributeRepository;
import com.example.pos.connection1.feature.country.CountryRepository;
import com.example.pos.connection1.feature.product.dto.ProductDataRequest;
import com.example.pos.connection1.feature.uom.UomRepository;
import com.example.pos.connection1.feature.vendor.VendorRepository;
import com.example.pos.connection1.repository.FileStoreRepository;
import com.example.pos.connection1.repository.ImportDetailRepository;
import com.example.pos.connection1.feature.product.ProductRepository;
import com.example.pos.connection1.repository.productProjection.ProductProjection;
import com.example.pos.connection1.repository.sourceDataRepository.TaxProductRepository;
import com.example.pos.connection1.service.ImportService;
import com.example.pos.connection1.util.exception.customeException.JavaNotFoundByIdGiven;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final FileStoreRepository fileStore;
    private final ImportDetailRepository repoImp;
    private final ImportService service;
    private final VendorRepository vendorRepository;
    private final TaxProductRepository taxProductRepository;
    private final CountryRepository countryRepository;
    private final UomRepository uomRepository;
    private final AttributeRepository attributeRepository;


    public Product addProduct(ProductDataRequest p, MultipartFile file) throws IOException {

        attributeRepository.findById(p.attributeId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attribute Id has not been found ."));

        uomRepository.findById(p.uomId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Uom Id has not been found ."));

        countryRepository.findById(p.countryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Country Id has not been found ."));

        taxProductRepository.findById(p.taxId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Tax Id has not been found ."));

        if (repo.existsByBarcode(p.barcode())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Barcode already exist in system .");
        }

        // validate vendorUuid
        if (!vendorRepository.existsById(p.vendorId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "vendorId has not been found .");
        }

        Product pro = new Product();

        // pro.setProductActiveId(p.productActiveId());
        // pro.setVendorId(p.vendorId());
        // pro.setCountryId(p.countryId());
        // pro.setAttributeId(p.attributeId());
        pro.setChoices(p.choice());
        // pro.setUomId(p.uomId());
        pro.setMargin(p.margin());
        pro.setCatId(p.catId());
        pro.setProNameKh(p.proNameKh());
        pro.setProNameEn(p.proNameEn());
        pro.setCost(p.cost());
        pro.setPrice(p.price());
        // pro.setTaxId(p.taxId());
        pro.setCreateBy(p.createBy());
        pro.setBarcode(p.barcode());
        // pro.setBrandId(p.brandId());
        pro.setDiscount(BigDecimal.valueOf(0));

        /*
         * proQty just use to check codition with import
         * case user add qty import will be working
         * case user not add qty import not working
         * column name pro_qty in table pos_product will have value 0 fixes
         */

        // pro.setProQty(0);

        // pro.setDiscountPercentag(p.getDiscountPercentag().isEmpty() ? "0" :
        // p.getDiscountPercentag());
//        pro.setProductStatus(p.getProductStatus()); // for detail product in or out stock
        if (file == null || file.isEmpty()) {
            pro.setProImageName(JavaConstant.defaultNameImage);
        } else {
            String fileName = UUID.randomUUID().toString();
            FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
            fileStore.save(f);
            pro.setProImageName(fileName);
        }

        repo.save(pro);

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

    public Product editProduct(int id, Product editProduct, MultipartFile file) throws IOException {
        Product previousPro = repo.findById(id).get();
        String fileName = previousPro.getProImageName();

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
//        previousPro.setProductActive(editProduct.getProductActive());
        // previousPro.setVendorId(editProduct.getVendorId());
        // previousPro.setCountryId(editProduct.getCountryId());
        // previousPro.setAttributeId(editProduct.getAttributeId());
        previousPro.setChoices(editProduct.getChoices());
        // previousPro.setUomId(editProduct.getUomId());
        previousPro.setMargin(editProduct.getMargin());

        previousPro.setProNameKh(editProduct.getProNameKh());
        previousPro.setProNameEn(editProduct.getProNameEn());
        // previousPro.setTaxId(editProduct.getTaxId());
        previousPro.setProductStatus(editProduct.getProductStatus());

        // previousPro.setCostKhr(editProduct.getCostKhr());
        previousPro.setCost(editProduct.getCost());
        // previousPro.setPriceKhr(editProduct.getPriceKhr());
        previousPro.setPrice(editProduct.getPrice());
        // previousPro.setWeight(editProduct.getWeight());
        previousPro.setBarcode(editProduct.getBarcode());
        previousPro.setDiscount(editProduct.getDiscount());
        // previousPro.setBrandId(editProduct.getBrandId());
        // previousPro.setProductStatus(editProduct.getProductStatus()); // for detail
        // product in or out stock
        // previousPro.setUnitTypeId(editProduct.getUnitTypeId());
        previousPro.setCatId(editProduct.getCatId());
        // previousPro.setNote(editProduct.getNote());
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
        obj.setStatus(false);
        obj.setIsDeleted(true);
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
