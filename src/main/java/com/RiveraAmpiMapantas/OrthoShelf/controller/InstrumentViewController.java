package com.RiveraAmpiMapantas.OrthoShelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.RiveraAmpiMapantas.OrthoShelf.domain.InstrumentView;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.CuttingInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.GraspingInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.ImpactingInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.RetractorInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.WireInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable.ConsumableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.BorrowableRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.ConsumableRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.CuttingRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.GraspingRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.ImpactingRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.InstrumentViewRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.RetractorRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.WireRepository;

@Controller
@RequestMapping("/index")
public class InstrumentViewController {

    @Autowired
    private InstrumentViewRepository repo;

    @GetMapping({"", "/"})
    public String showInstrumentList(Model model) {
        List<InstrumentView> instrumentsView = repo.findAll();
        model.addAttribute("instruments", instrumentsView);
        return "index";
    }

    @Autowired
    private BorrowableRepository borrowRepo;

    //REST endpoint to get Borrowable instruments data
    @GetMapping("/borrowable/data")
    @ResponseBody
    public List<BorrowableInstrument> getBorrowableData() {
        return borrowRepo.findAll();
    }

    @Autowired
    private ConsumableRepository consumeRepo;

    //REST endpoint for Consumable instruments data
    @GetMapping("/consumable/data")
    @ResponseBody
    public List<ConsumableInstrument> getConsumableData() {
        return consumeRepo.findAll();
    }

    @Autowired
    private CuttingRepository cuttingRepo;

    //REST endpoint for Cutting instruments data
    @GetMapping("/cutting/data")
    @ResponseBody
    public List<CuttingInstrument> getCuttingData() {
        return cuttingRepo.findAll();
    }

    @Autowired
    private GraspingRepository graspingRepo;

    //REST endpoint for Grasping instruments data
    @GetMapping("/grasping/data")
    @ResponseBody
    public List<GraspingInstrument> getGraspingData() {
        return graspingRepo.findAll();
    }

    @Autowired
    private RetractorRepository retractorRepo;

    //REST endpoint for Retractor instruments data
    @GetMapping("/retractor/data")
    @ResponseBody
    public List<RetractorInstrument> getRetractorData() {
        return retractorRepo.findAll();
    }

    @Autowired
    private ImpactingRepository impactingRepo;

    //REST endpoint for Impacting instruments data
    @GetMapping("/impacting/data")
    @ResponseBody
    public List<ImpactingInstrument> getImpactingData() {
        return impactingRepo.findAll();
    }

    @Autowired
    private WireRepository wireRepo;

    //REST endpoint for Wire instruments data
    @GetMapping("/wire/data")
    @ResponseBody
    public List<WireInstrument> getWireData() {
        return wireRepo.findAll();
    }
}
