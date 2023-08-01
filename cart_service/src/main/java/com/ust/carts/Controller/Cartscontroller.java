package com.ust.carts.Controller;
import com.ust.carts.Model.Carts;
import com.ust.carts.Repository.Cartsrepo;
import com.ust.carts.VO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins="http://localhost:5173")
public class Cartscontroller {

    @Autowired
    private Cartsrepo cartsrepo;

    @PostMapping
    public ResponseEntity<Carts> createUser(@RequestBody Carts cart) {
        Carts savedUser = cartsrepo.save(cart);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

     //Read operation
    @GetMapping("/{userid}")
    public ResponseEntity<List<Carts>> getUser(@PathVariable("userid") Integer userid) {
        var cart = cartsrepo.findByUserid(userid).orElse(null);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userid}")
    public ResponseEntity<Carts> updateUser(@PathVariable("userid") Integer userid, @RequestBody Carts cart) {
        if (cartsrepo.existsByUserid(userid)) {
            cart.setUserid(userid);
            Carts updatedUser = cartsrepo.save(cart);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    Delete operation
    @DeleteMapping("{userid}/{cartid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("cartid") Integer cartid,@PathVariable("userid") Integer userid) {
        if (cartsrepo.existsByUserid(userid)) {
            Optional<List<Carts>> cartsOptional = cartsrepo.findByUserid(userid);
             var carts= cartsOptional.get();
             for(Carts cart : carts){
                 if(cart.getCartid()==cartid){
                     cartsrepo.deleteById(cartid);
                 }
             }
        return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    ResponseEntity<List<Carts>> findAll(){
        List<Carts> products = cartsrepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);

    }
}
