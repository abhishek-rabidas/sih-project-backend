it branchpackage org.aicte.sih.SIHProject.api.college.controller;

import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.api.college.services.CollegeService;
import org.aicte.sih.SIHProject.api.college.services.CollegeServiceImplementation;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/college")
public class CollegeController {


    @Autowired
    private CollegeService collegeService;

    @PostMapping
    public ResponseEntity<APIResponse<CollegeEntity>> register(@RequestBody CollegeRegistrationRequest request) {
        return null;
    }


    @GetMapping("/getRegisteredColleges")
    public List<CollegeEntity> getCollegeList()
    {

        return collegeService.getRegisteredColleges();
    }
}
