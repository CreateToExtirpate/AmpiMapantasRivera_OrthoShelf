package com.RiveraAmpiMapantas.OrthoShelf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.InstrumentRepository;
import com.RiveraAmpiMapantas.OrthoShelf.service.InstrumentService;

@Controller
@RequestMapping("/api/instrument")
public class InstrumentController {

    @Autowired
    InstrumentService instrumentService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> createInstrument(@RequestParam Map<String, String> params) {
        InstrumentDTO instrumentDto = new InstrumentDTO();

        instrumentDto.setName(params.get("name"));
        instrumentDto.setPrice(Double.parseDouble(params.get("price")));
        instrumentDto.setStock(Integer.parseInt(params.get("stock")));
        instrumentDto.setDescription(params.get("description"));
        instrumentDto.setCategory(params.get("category"));
        instrumentDto.setType(params.get("type"));
        instrumentDto.setSubtype(params.get("subtype"));
        instrumentDto.setUsetype(params.get("usetype"));
        instrumentDto.setSize(params.get("size"));

        instrumentService.createInstrument(instrumentDto);

        return ResponseEntity.ok("Instrument created");
    }

    @GetMapping("/edit")
    @ResponseBody
    public ResponseEntity<InstrumentDTO> getInstrument(
            @RequestParam int id,
            @RequestParam String category
    ) {
        InstrumentDTO dto = instrumentService.getInstrumentById(id, category);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity<String> editInstrument(
            @PathVariable int id,
            @RequestParam Map<String, String> params
    ) {
        InstrumentDTO instrumentDto = new InstrumentDTO();

        instrumentDto.setId(id);
        instrumentDto.setName(params.get("name"));
        instrumentDto.setPrice(Double.parseDouble(params.get("price")));
        instrumentDto.setStock(Integer.parseInt(params.get("stock")));
        instrumentDto.setDescription(params.get("description"));
        instrumentDto.setCategory(params.get("category"));

        instrumentService.updateInstrument(instrumentDto);

        return ResponseEntity.ok("Instrument saved!");
    }

    @Autowired
    private InstrumentRepository instrumentRepo;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInstrument(
            @PathVariable int id
    ) {
        Instrument instrument = instrumentRepo.findById(id).
                orElseThrow(() -> new RuntimeException("Instrument ID not found."));

                instrumentRepo.delete(instrument);
        return ResponseEntity.ok("Instrument deleted!");
    }
}
