package com.RiveraAmpiMapantas.OrthoShelf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.CuttingInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.GraspingInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.ImpactingInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.RetractorInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.WireInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable.ConsumableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable.GauzePad;
import com.RiveraAmpiMapantas.OrthoShelf.factory.BorrowableDispatcher;
import com.RiveraAmpiMapantas.OrthoShelf.factory.ConsumableFactory;
import com.RiveraAmpiMapantas.OrthoShelf.factory.GauzepadFactory;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.BorrowableRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.ConsumableRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.CuttingRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.GauzepadRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.GraspingRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.ImpactingRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.RetractorRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.WireRepository;

@Service
public class InstrumentService {

    @Autowired
    private CuttingRepository cuttingRepo;
    @Autowired
    private GraspingRepository graspingRepo;
    @Autowired
    private RetractorRepository retractorRepo;
    @Autowired
    private ImpactingRepository impactingRepo;
    @Autowired
    private WireRepository wireRepo;
    @Autowired
    private ConsumableRepository consumableRepo;
    @Autowired
    private BorrowableRepository borrowableRepo;
    @Autowired
    private GauzepadRepository gauzepadRepo;
    @Autowired
    private BorrowableDispatcher borrowableDispatcher;
    @Autowired
    private ConsumableFactory consumableFactory;
    @Autowired
    private GauzepadFactory gauzePadFactory;

    
    public void createInstrument(InstrumentDTO dto) {
        if (dto.getCategory().equals("BORROWABLE")) {
            BorrowableInstrument borrow = borrowableDispatcher.createInstrument(dto);
            chooseRepository(borrow, dto.getType());
        } else if (dto.getType().equals("GAUZEPAD")) {
            GauzePad gauze = gauzePadFactory.createFromDto(dto);
            gauzepadRepo.save(gauze);
            System.out.println("New Gauzepad Created!");
        } else {
            ConsumableInstrument consumable = consumableFactory.createFromDto(dto);
            consumableRepo.save(consumable);
            System.out.println("New Consumable Instrument Created!");
        }
    }

    private void chooseRepository(BorrowableInstrument borrow, String type) {
        switch (type) {
            case "CUTTING" ->
                cuttingRepo.save((CuttingInstrument) borrow);
            case "GRASPING" ->
                graspingRepo.save((GraspingInstrument) borrow);
            case "RETRACTOR" ->
                retractorRepo.save((RetractorInstrument) borrow);
            case "IMPACTING" ->
                impactingRepo.save((ImpactingInstrument) borrow);
            case "WIRE" ->
                wireRepo.save((WireInstrument) borrow);
        }
        System.out.println("New Borrowable Instrument Created!");
    }

    public InstrumentDTO getInstrumentById(int id, String category) {
        if (category.equals("BORROWABLE")) {
            return borrowableRepo.findById(id)
                    .map(BorrowableInstrument::toDto)
                    .orElseThrow(() -> new RuntimeException("Instrument Not Found!"));
        } else {
            return consumableRepo.findById(id)
                    .map(ConsumableInstrument::toDto)
                    .orElseThrow(() -> new RuntimeException("Instrument Not Found!"));
        }

    }

    public void updateInstrument(InstrumentDTO dto) {
        if (dto.getCategory().equals("BORROWABLE")) {
            BorrowableInstrument borrow = borrowableRepo.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Instrument Not Found!"));
            borrow.setName(dto.getName());
            borrow.setPrice(dto.getPrice());
            borrow.setStock(dto.getStock());
            borrow.setDescription(dto.getDescription());

            borrowableRepo.save(borrow);
        } else {
            ConsumableInstrument consumable = consumableRepo.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Instrument Not Found!"));
            
            consumable.setName(dto.getName());
            consumable.setPrice(dto.getPrice());
            consumable.setStock(dto.getStock());
            consumable.setDescription(dto.getDescription());

            consumableRepo.save(consumable);
            
        }
    }
}
