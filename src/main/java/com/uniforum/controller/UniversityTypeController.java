package com.uniforum.controller;
import com.uniforum.dto.UniversityTypeDto;
import com.uniforum.model.UniversityType;
import com.uniforum.service.UniversityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/universityType")
@CrossOrigin
public class UniversityTypeController {
        @Autowired
        private UniversityTypeService universityTypeService;

        @PostMapping("/add")
        public ResponseEntity<Void> createUniversityType(@RequestBody UniversityType newUniversityType) {
            UniversityType universityType = universityTypeService.createUniversityType(newUniversityType);
            if(universityType != null)
                return new ResponseEntity<>(HttpStatus.CREATED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @GetMapping("/getAll")
        public List<UniversityTypeDto> getAllUniversityType(){
            return universityTypeService.getAllUniversityType().stream().map(u -> new UniversityTypeDto(u)).toList();
        }

        @GetMapping("/{universityTypeId}")
        public UniversityTypeDto getUniversityTypeById(@PathVariable("universityTypeId")String universityTypeId){
            UniversityType universityType = universityTypeService.getUniversityTypeById(universityTypeId);
            return new UniversityTypeDto(universityType);
        }

        @DeleteMapping("/{universityTypeId}")
        public String deleteUniversityType(@PathVariable("universityTypeId") String universityTypeId){
            return universityTypeService.deleteUniversityType(universityTypeId);
        }

        @PutMapping("/{universityTypeId}")
        public ResponseEntity<Void> updateUniversityTypeById(@PathVariable("universityTypeId")String universityTypeId, @RequestBody UniversityType newUniversityType) {
            UniversityType universityType = universityTypeService.updateUniversityTypeById(universityTypeId, newUniversityType);
            if (universityType != null)
                return new ResponseEntity<>(HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
