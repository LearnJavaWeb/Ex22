package thinhluffy.com.demoJPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import thinhluffy.com.demoJPA.model.Customer;
import thinhluffy.com.demoJPA.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    //Tao create
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/customers/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/customers/create")//tu action den voi postmapping de tao khach hang
    public ModelAndView saveCreateForm(@ModelAttribute("customer")Customer customer)
    {
        customerService.save(customer);

        ModelAndView modelAndView = new ModelAndView("/customers/create");
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("message","Ok");
        return  modelAndView;
    }
    //Hien thi khach hang
    @GetMapping("/customers/list")
    public ModelAndView listCustomer()
    {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customers/list");//tao file list.html trong thu muc customers
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }
    //Sua khach hang
    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer!=null){
            ModelAndView modelAndView = new ModelAndView("/customers/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }
    @PostMapping("/edit/")
    public ModelAndView save(@ModelAttribute("customer")Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customers/edit");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","Ok");
        return modelAndView;
    }
    //Xoa Khach Hang
    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer!=null){
            ModelAndView modelAndView = new ModelAndView("/customers/delete");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }
    @PostMapping("/delete/")
    public ModelAndView delete(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        ModelAndView modelAndView = new ModelAndView("/customers/delete");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","Da Xoa");
        return modelAndView;
    }

}
