package com.uniforum.controller;
import com.uniforum.dto.UniversityDto;
import com.uniforum.model.University;
import com.uniforum.request.UniversityCreateRequest;
import com.uniforum.request.UniversityUpdateRequest;
import com.uniforum.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/university")
@CrossOrigin
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/add")
    public ResponseEntity<Void> createUniversity(@RequestBody UniversityCreateRequest universityCreateRequest) {
        University university = universityService.createUniversity(universityCreateRequest);
        if(university != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAll")
    public List<UniversityDto> getAllUniversity(){
        return universityService.getAllUniversity().stream().map(u -> new UniversityDto(u)).toList();
    }

    @GetMapping("/{universityId}")
    public UniversityDto getUniversityById(@PathVariable("universityId")String universityId){
        University university = universityService.getUniversityById(universityId);
        return new UniversityDto(university);
    }

    @DeleteMapping("/{universityId}")
    public String deleteUniversityById(@PathVariable("universityId") String universityId){
        return universityService.deleteUniversityById(universityId);
    }

    @PutMapping("/{universityId}")
    public University updateUniversityById(@PathVariable("universityId")String universityId, @RequestBody UniversityUpdateRequest updateUniversity){
        return universityService.updateUniversityById(universityId,updateUniversity);
    }


}
