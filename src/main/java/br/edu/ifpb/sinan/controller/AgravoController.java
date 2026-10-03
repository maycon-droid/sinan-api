package br.edu.ifpb.sinan.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import br.edu.ifpb.sinan.model.Agravo;
import br.edu.ifpb.sinan.service.AgravoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/agravos")
public class AgravoController {
    private final AgravoService agravoService;

    public AgravoController(AgravoService agravoService) {
        this.agravoService = agravoService;
    }

    @GetMapping
    public ResponseEntity<List<Agravo>> getAllAgravos() {
        List<Agravo> agravos = agravoService.getAllAgravos();
        return ResponseEntity.ok(agravos);
    }

    @PostMapping 
    public ResponseEntity<Agravo> salvarAgravo(@Valid @RequestBody Agravo agravo) {
        Agravo savedAgravo = agravoService.salvarAgravo(agravo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAgravo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Agravo> getAgravoById(@PathVariable Long id) {
        Agravo agravo = agravoService.getAgravoById(id);
        if(agravo != null) {
            return ResponseEntity.ok(agravo);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agravo> atualizarAgravo(@PathVariable Long id,@Valid @RequestBody Agravo novosDados) {
        Agravo agravoAtualizado = agravoService.atualizarAgravo(id, novosDados);
        if(agravoAtualizado != null) {
            return ResponseEntity.ok(agravoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgravo(@Valid @PathVariable Long id) {
        Agravo agravo = agravoService.getAgravoById(id);
        if(agravo == null) {
            return ResponseEntity.notFound().build();
        }
        agravoService.deleteAgravo(id);
        return ResponseEntity.noContent().build();
    }
}
