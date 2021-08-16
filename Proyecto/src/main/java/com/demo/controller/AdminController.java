package com.demo.controller;

import com.demo.models.Administrator;
import com.demo.services.AdministratorService;
import com.demo.models.Category;
import com.demo.models.Product;
import com.demo.models.Supplier;
import com.demo.services.CategoryService;
import com.demo.services.ProductService;
import com.demo.services.SupplierService;
import com.demo.utility.CategoryUtility;
import com.demo.utility.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    private String userName;
    private int idAdmin;

    /**
     * Este endpoint mostrara el dashboard del administrador
     * desde este punto se tiene acceso a todas las acciones
     * disponibles de la aplicacion.
     *
     * @param userName
     * @param model
     * @return
     */
    @GetMapping("/admin/management/{userName}")
    public String getRegister(@PathVariable("userName") String userName, Model model) {

        this.userName = userName;
        this.idAdmin = getAdministrator(administratorService.findAll(), userName).getId();
        Product product;
        Category category;
        Supplier supplier;

        Administrator administrator = getAdministrator(
                administratorService.findAll(),
                userName
        );

        model.addAttribute("administrator", administrator);
        model.addAttribute("product", new Product());
        model.addAttribute("category", new Category());
        model.addAttribute("supplier", new Supplier());

        model.addAttribute("administrators", administratorService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());








        return "management";
    }



    /**
     * Endpoint donde se almacena en la base de datos el producto.
     *
     * @param product
     * @return
     */
    @PostMapping("/auth/save/product")
    public String saveProduct(@Valid Product product, Errors errors) {
        System.out.println(errors.hasErrors());
        if (errors.hasErrors()) {
            return "redirect:/admin/management/"+userName;
        }

        productService.createProduct(product);
        return "redirect:/admin/management/" + this.userName;
    }

    /**
     * Endpoint donde se almacena en la base de datos la categoria.
     *
     * @param category
     * @return
     */
    @PostMapping("/auth/save/category")
    private String saveCategory(@Valid Category category, Errors errors) {
        System.out.println(errors.hasErrors());
        if (errors.hasErrors()) {
            return "redirect:/admin/management/"+userName;
        }

        categoryService.createCategory(category);
        return "redirect:/admin/management/" + this.userName;
    }

    /**
     * Endpoint donde se almacena en la base de datos en proveedor.
     *
     * @param supplier
     * @return
     */
    @PostMapping("/auth/save/supplier")
    public String saveSupplier(@Valid Supplier supplier, Errors errors) {

        if (errors.hasErrors()) {
            return "redirect:/admin/management/"+userName;
        }

        supplierService.createSupplier(supplier);
        return "redirect:/admin/management/" + this.userName;
    }

    //Endpoints administrativos

    /**
     * para la nueva feature vas a tener que recibir
     * el id de la categoria por parametro en el
     * /management/stock/by/category/{idCategory}
     * por lo que desde el /management/stock tienes que mandar un
     * id mas no un objeto.
     *
     * una vez tengas el id en el /management/stock/by/category/{idCategory}
     * tienes que mandar a traer la categoria a la que corresponde esa id
     * para mandar el objeto a la capa del view
     *
     * tienes que mandar el id a la vista para poder usarlo en el navbar
     * para regresar al punto anterior a la hora de actualizar y eliminar
     */

    /**
     * Revisar este punto para la nueva feature
     * @param model
     * @return
     */
    @GetMapping("/management/stock")
    public String getStockViewer(Model model) {
        List<Category> categories = categoryService.findAll();

        model.addAttribute("idAdmin",idAdmin);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryUtility", new CategoryUtility());

        return "stockviewer";
    }

    /**
     * revisar este punto para la nueva feature
     * @param idCategory
     * @param model
     * @return
     */
    @GetMapping("/management/stock/by/category/{idCategory}")
    public String postStockViewer(@PathVariable int idCategory, Model model) {

        List<Category> categories = categoryService.findAll();

        Optional<Category> category = categoryService.findById(idCategory);
        String nameCategory = category.get().getNameCategory();

        model.addAttribute("filteredProducts", filteredProductsByCategory(category
                .get()
                .getIdCategory()
        )); //filtrar productos por categorias

        model.addAttribute("nameCategory",nameCategory);
        model.addAttribute("categories", categories);
        model.addAttribute("idCategory", idCategory);

        return "stockviewerByCategory";
    }

    @PostMapping("/redirect/filter/category")
    public String postFilterCategory(CategoryUtility categoryUtility) {
        if (categoryUtility.getId() == 0) {
            return "redirect:/management/stock";
        }
        return "redirect:/management/stock/by/category/"+categoryUtility.getId();
    }

    @GetMapping("/switch/admin")
    public String getSwitch() {
        return "redirect:/admin/management/" + userName;
    }

    /**
     * revisar este punto para la nueva feature
     * @param idProduct
     * @param model
     * @return
     */
    @GetMapping("/product/update/{idProduct}")
    public String getUpdate(@PathVariable int idProduct, Model model) {
        Optional<Product> product = productService.findById(idProduct);
        int idCategory = product.get().getCategory().getIdCategory();

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("product", product.get());
        model.addAttribute("idCategory",idCategory);
        return "updateFormProduct";
    }

    /**
     * revisar este punto para la nueva feature
     * @param product
     * @return
     */
    @PostMapping("/auth/update/product")
    public String postUpdateProduct(@Valid Product product, Errors errors, Model model) {

        if (errors.hasErrors()) {
            int idCategory = product.getIdProduct();

            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("suppliers", supplierService.findAll());
            model.addAttribute("product", product);
            model.addAttribute("idCategory",idCategory);
            return "updateFormProduct";
        }

        int idCategory = product.getCategory().getIdCategory();
        productService.createProduct(product);
        return "redirect:/management/stock/by/category/"+idCategory;
    }

    /**
     * revisar este punto para la nueva feature
     * @param idProduct
     * @return
     */
    @GetMapping("/product/delete/{idCategory}/{idProduct}")
    public String deleteProduct(@PathVariable int idCategory,@PathVariable int idProduct) {
        productService.removeProduct(idProduct);
        return "redirect:/management/stock/by/category/"+idCategory;
    }

    /**
     * Categorias endpoints
     *
     * @param model
     * @return
     */
    @GetMapping("/management/categories")
    public String getCategories(Model model) {
        model.addAttribute("idAdmin",idAdmin);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("validation", new Validation());
        return "categoriesviewer";
    }

    @GetMapping("/category/update/{idCategory}")
    public String getUpdateCategory(@PathVariable int idCategory, Model model) {
        Optional<Category> category = categoryService.findById(idCategory);
        model.addAttribute("category", category.get());
        return "updateFormCategory";
    }

    @PostMapping("/auth/update/category")
    public String postUpdateCategory(@Valid Category category, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("category", category);
            return "updateFormCategory";
        }

        categoryService.createCategory(category);
        return "redirect:/management/categories";
    }

    @GetMapping("/category/delete/{idCategory}")
    public String deleteCategory(@PathVariable int idCategory) {
        categoryService.deleteCategory(idCategory);
        return "redirect:/management/categories";
    }

    /**
     * suppliers endpoints
     *
     * @param model
     * @return
     */
    @GetMapping("/management/suppliers")
    public String getSuppliers(Model model) {
        model.addAttribute("idAdmin",idAdmin);
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("validation", new Validation());
        return "supplierviewer";
    }

    @GetMapping("/supplier/update/{idSupplier}")
    public String getSupplier(@PathVariable int idSupplier, Model model) {
        Optional<Supplier> supplier = supplierService.findById(idSupplier);
        model.addAttribute("supplier", supplier.get());
        return "updateFormSupplier";
    }

    @PostMapping("/auth/update/supplier")
    public String postUpdateSupplier(@Valid Supplier supplier, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("supplier", supplier);
            return "updateFormSupplier";
        }

        supplierService.createSupplier(supplier);
        return "redirect:/management/suppliers";
    }

    @GetMapping("/supplier/delete/{idSupplier}")
    public String deleteSupplier(@PathVariable int idSupplier) {
        supplierService.removeSupplier(idSupplier);
        return "redirect:/management/suppliers";
    }



    /**
     * Queda pendiente recuerda que la clave para resolverlo puede ser crear una clase para esto
     */
    private boolean haveAnyProduct() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Este metodo me sirve para filtrar los productos por categorias
     * recibe por parametro el id de la categoria y retorna una
     * lista con los productos relacionados
     * @param idCategory
     * @return
     */
    private List<Product> filteredProductsByCategory(int idCategory) {
        List<Product> list = new LinkedList<>();
        List<Product> products = productService.findAll();
        for (Product product : products) {
            if (product.getCategory().getIdCategory() == idCategory) {
                list.add(product);
            }
        }
        return list;
    }

    /**
     * Este metodo me sirve para filtrar los productos por proveedores
     * recibe por parametro el id de la categoria y retorna una
     * lista con los productos relacionados
     * @param idSupplier
     * @return
     */
    private List<Product> filteredProductsBySuppliers(int idSupplier) {
        List<Product> list = new LinkedList<>();
        List<Product> products = productService.findAll();
        for (Product product : products) {
            if (product.getSupplier().getIdSupplier() == idSupplier) {
                list.add(product);
            }
        }
        return list;
    }

    public Administrator getAdministrator(List<Administrator> administrators, String userName) {
        Administrator aux = null;
        for (Administrator administrator : administrators) {
            if (administrator.getUserName().equals(userName)) {
                aux = administrator;
                break;
            }
        }
        return aux;
    }
}
