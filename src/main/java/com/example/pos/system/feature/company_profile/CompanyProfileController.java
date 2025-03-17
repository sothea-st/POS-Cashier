package com.example.pos.system.feature.company_profile;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.company_profile.dto.BusinessRequest;
import com.example.pos.system.feature.company_profile.dto.IndividualRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companyProfiles")
public class CompanyProfileController {
    // inject bean service
    private final CompanyProfileService companyProfileService;

    @PostMapping("/individual")
    public ResponseSuccess createIndividual(@Valid @RequestBody IndividualRequest individualRequest){
        return companyProfileService.createIndividual(individualRequest);
    }

    @PutMapping("/individual/{id}")
    public ResponseSuccess updateIndividual( @PathVariable Integer id , @RequestBody IndividualRequest individualRequest){
        return companyProfileService.updateIndividual(id,individualRequest);
    }

    @GetMapping
    public JavaCollectionResponse<?> read(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam String code
    ) {
        return companyProfileService.read(pageNumber,pageSize,code);
    }

    @GetMapping("/search")
    public JavaCollectionResponse<?> search(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam String code,
            @RequestParam String search
    ) {
        return companyProfileService.search(pageNumber,pageSize,code,search);
    }

    @GetMapping("/{id}")
    public JavaResponse<?> readByIdAndCode(@PathVariable Integer id , @RequestParam String code){
        return companyProfileService.readByIdAndCode(id,code);
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess deleteByIdAndCode(@PathVariable Integer id , @RequestParam String code){
        return companyProfileService.deleteByIdAndCode(id,code);
    }

    @PostMapping("/business")
    public ResponseSuccess createBusiness(@RequestBody BusinessRequest businessRequest){
        return companyProfileService.createBusiness(businessRequest);
    }

    @PutMapping("/business/{id}")
    public ResponseSuccess createBusiness(@PathVariable Integer id , @RequestBody BusinessRequest businessRequest){
        return companyProfileService.updateBusiness(id,businessRequest);
    }

}
