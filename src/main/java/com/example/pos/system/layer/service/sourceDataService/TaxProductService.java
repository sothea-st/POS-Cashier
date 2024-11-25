package com.example.pos.system.layer.service.sourceDataService;

// import com.example.pos.connection1.repository.sourceDataRepository.TaxProductRepository;

// @Service
// public class TaxProductService {
//      @Autowired
//      private TaxProductRepository repo;

//      public TaxProduct add(TaxProduct t) {
//           TaxProduct obj = new TaxProduct();
//           obj.setTaxName(t.getTaxName());
//           obj.setCreateBy(t.getCreateBy());
//           obj.setRateTax(t.getRateTax());
//           repo.save(obj);
//           return obj;
//      }

//      public List<TaxProductProjection> read(){
//           return repo.getTax();
//      }

//      public TaxProductProjection getById(int id) {
//           Optional<TaxProductProjection> data = repo.getById(id);
//           if( data == null || data.isEmpty() ) throw new JavaNotFoundByIdGiven();
//           return data.get();
//      }

//      public TaxProduct update(int id , TaxProduct t) {
//           Optional<TaxProduct> data = repo.findById(id);
//           if( data == null || data.isEmpty() ) throw new JavaNotFoundByIdGiven();
//           TaxProduct tt = data.get();
//           tt.setTaxName(t.getTaxName());
//           tt.setRateTax(t.getRateTax());
//           repo.save(tt);
//           return tt;
//      }


//      public void delete(int id , TaxProduct t) {
//           Optional<TaxProduct> data = repo.findById(id);
//           if( data == null || data.isEmpty() ) throw new JavaNotFoundByIdGiven();
//           TaxProduct obj = data.get();
//           obj.setStatus(t.isStatus());
//           obj.setDeleted(t.isDeleted());
//           repo.save(obj);
//      }

//      public List<TaxProductProjection> searchTax(String searchvalue){
//           return repo.searchTax(searchvalue);
//      }

// }
