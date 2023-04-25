package com.codegym.springboot_modul_6.service.thirdpartyservice;
import com.codegym.springboot_modul_6.model.fe_bo_model.entity.Store;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.*;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.AccountDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.OrderDetailsSFModel;
import com.codegym.springboot_modul_6.repository.fe_bo_repository.StoreRepository;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.OrderSFModel;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IOrderRepository;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.security.JwtProvider;
import com.codegym.springboot_modul_6.service.fe_sf_service.AccountService;
import com.codegym.springboot_modul_6.service.fe_sf_service.CategoryService;
import com.codegym.springboot_modul_6.service.fe_sf_service.RolesService;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IProductRepositorySF;
import com.codegym.springboot_modul_6.util.LongMapper;
import com.codegym.springboot_modul_6.util.RequestMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ThirdService {

    @Autowired
    private IProductRepositorySF productRepositorySF;

    private static final CategoryCache categoryCache = CategoryCache.getCategoryCache();

    @Autowired
    private LongMapper mapper;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private RolesService rolesService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private IProductDetailSFRepository productSFDetailRepository;
    @Autowired
    private StoreRepository storeRepository;

    @Value("${store.defaultStoreImg}")
    private String INIT_STORE_IMG;


    public Account signUp(AccountDto accountDto){
        Account account = new Account();
        AccountRoles accountRoles = new AccountRoles();
        List<AccountRoles> accountRolesList = new ArrayList<>();

        Roles roles = rolesService.findRolesByName("ROLE_USER").get();
        account = requestMapper.toAccount(accountDto);
        accountRoles.setAccount(account);
        accountRoles.setRoles(roles);
        accountRolesList.add(accountRoles);
        account.setRolesList(accountRolesList);
        accountService.save(account);
        return account;
    }

    public AccountDto getUser(String username){
        Account account = accountService.findAccountByUsername(username).get();
        if (account != null){
            AccountDto accountDto = requestMapper.toAccountDto(account);
            return accountDto;
        }
        return null;
    }
    @Transactional

    public Account update (AccountDto accountDto){
        Account account = accountService.findAccountByUsername(accountDto.getUsername()).get();
        String passwordDb = account.getPassword();
        if (account != null){
            account.setCity(accountDto.getCity());
            account.setDistrict(accountDto.getDistrict());
            account.setStreet(accountDto.getStreet());
            account.setFirstName(accountDto.getFirstName());
            account.setLastName(accountDto.getLastName());
            account.setPhone(accountDto.getPhone());
            account.setPassword(passwordDb);
            accountService.save(account);
            return account;
        }
        return null;
    }

    public boolean checkPassword (String password , String username){
       return accountService.checkLogin(username,password);
    }

    public void updatePassword(Account account,String password){
        account.setPassword(password);
        accountService.save(account);
    }

    @Transactional
    public Account signUpOwner(AccountDto accountDto){
        Account account = new Account();
        AccountRoles accountRoles = new AccountRoles();
        List<AccountRoles> accountRolesList = new ArrayList<>();

        Roles roles = rolesService.findRolesByName("ROLE_OWNER").get();
        account = requestMapper.toAccount(accountDto);
        accountRoles.setAccount(account);
        accountRoles.setRoles(roles);
        accountRolesList.add(accountRoles);
        account.setRolesList(accountRolesList);
        accountService.save(account);

        String accountName = account.getUsername();
        Store store = new Store();
        store.setName(accountName);
        store.setImage(INIT_STORE_IMG);
        store.setAccount(account);
        storeRepository.save(store);

        return account;
    }

    public String login(AccountDto accountDto){
        boolean isLogin = accountService.checkLogin(accountDto.getUsername(), accountDto.getPassword());
        Account account = accountService.findAccountByUsername(accountDto.getUsername()).get();
        List<AccountRoles> roles = account.getRolesList();
        boolean isRole  = false;
        for (AccountRoles role :
             roles ) {
            if (role.getRoles().getName().equals("ROLE_USER")) {
                isRole = true;
                break;
            }
        }
        if (isLogin && isRole){
            String jwt = jwtProvider.generateTokenLogin(account);
          return jwt;
        }
        return null;
    }

    public String loginOwner(AccountDto accountDto){
        boolean isLogin= accountService.checkLogin(accountDto.getUsername(), accountDto.getPassword());
        Account account = accountService.findAccountByUsername(accountDto.getUsername()).get();
        List<AccountRoles> roles = account.getRolesList();
        boolean isRole  = false;
        for (AccountRoles role :
                roles ) {
            if (role.getRoles().getName().equals("ROLE_OWNER")) {
                isRole = true;
                break;
            }
        }
        if (isLogin && isRole){

            String jwt = jwtProvider.generateTokenLogin(account);
            return jwt;
        }
        return null;
    }

    public Account checkValidateEmail(String email){
        Account account = accountService.findAccountByUEmail(email).get();
        return account;
    }

    public Account checkValidateUsername(String username){
        Account account = accountService.findAccountByUsername(username).get();
        return account;
    }

    public Account checkValidatePhone(String phone){
        Account account = accountService.findAccountByPhone(phone).get();
        return account;
    }

    public Page<ProductSFDto> productSFDtoPage(Page<ProductSF> entity){
        List<ProductSFDto> productSFDtos = mapper.mapperProductSFDto(entity.getContent());
        if  (entity.getContent().size() == 0){
            throw new RuntimeException("No data");
        }
        Page<ProductSFDto> page = new PageImpl<>(productSFDtos, entity.getPageable(), entity.getTotalElements());
        return page;
    }

    public ProductSFDto getProductSFDto(String name) {
        ProductSF productSF = productRepositorySF.findProductSFByName(name).orElse(null);
        if(productSF != null) {
            List<ProductSFDetail> productSFDetailList = productSF.getProductSFDetail();
            List<Price> prices = productSF.getPrices();
            List<ProductSFDetailDto> productSFDetailDtoList = new ArrayList<>();
            for (ProductSFDetail p : productSFDetailList) {
                ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
                BeanUtils.copyProperties(p, productSFDetailDto);
                productSFDetailDtoList.add(productSFDetailDto);
            }
            List<PriceDto> priceDtos = new ArrayList<>();
            for (Price price : prices) {
                PriceDto priceDto = new PriceDto();
                BeanUtils.copyProperties(price, priceDto);
                priceDtos.add(priceDto);
            }
            ProductSFDto productSFDto = new ProductSFDto();
            BeanUtils.copyProperties(productSF, productSFDto);
            productSFDto.setProductSFDetailDtos(productSFDetailDtoList);
            productSFDto.setPriceDtos(priceDtos);
            productSFDto.setStoreName(productSF.getStore().getName());
            productSFDto.setStoreImage(productSF.getStore().getImage());
            return productSFDto;
        }
        return null;

    }

    @Transactional
    public ProductSF mapProductSF(ProductSFDto productSFDto) {
        List<ProductSFDetailDto> productSFDetailDtoList = productSFDto.getProductSFDetailDtos();
        List<ProductSFDetail> productSFDetailList = new ArrayList<>();
        ProductSF productSF = new ProductSF();
        for (ProductSFDetailDto productSFDetailDto: productSFDetailDtoList) {
            ProductSFDetail productSFDetail = new ProductSFDetail();
            BeanUtils.copyProperties(productSFDetailDto, productSFDetail);
            productSFDetail.setProductSF(productSF);
            productSFDetail.setStatus("true");
            productSFDetailList.add(productSFDetail);
        }
        BeanUtils.copyProperties(productSFDto, productSF);
        productSF.setProductSFDetail(productSFDetailList);
        List<PriceDto> priceDtos = productSFDto.getPriceDtos();
        List<Price> prices = new ArrayList<>();
        for (PriceDto priceDto : priceDtos) {
            Price price = new Price();
            BeanUtils.copyProperties(priceDto, price);
            price.setProductSF(productSF);
            prices.add(price);
        }
        productSF.setPrices(prices);
        String accountUsername = productSFDto.getAccountUsername();
        Long accountId = accountService.findAccountByUsername(accountUsername).get().getId();
        Store store = storeRepository.findByAccount_Id(accountId).get();
        productSF.setStore(store);
        return productSF;
    }

    public List<OrderSFModel> getListOrder(String username){
        Account account = accountService.findAccountByUsername(username).get();
        List<OrderSF> orderSFList = orderRepository.getAllByAccount_Id(account.getId());
        return requestMapper.orderSFModelList(orderSFList);
    }

    public List<OrderDetailsSFModel> getListOrderDetails (String orderCode){
        OrderSF orderSF = orderRepository.findByOrderCode(orderCode).orElseThrow(() -> new RuntimeException("Order Not Found"));
        List<OrderDetailsSFModel>  orderDetailsSFModelList = requestMapper.orderDetailsSFModelList(orderSF.getOrderDetailSFS());
        return orderDetailsSFModelList;
    }

    public List<ProductSFDto> getListProductDtoRandomByProductName ( String productName){
        ProductSF productSF = productRepositorySF.findProductSFByName(productName).orElse(null);
        if(productSF != null) {
            List<ProductSF> productSFList = productRepositorySF.findRandomProductYouLikeThis(productSF.getCategory(), productName);
            return mapper.mapperProductSFDto(productSFList);
        }
        return null;
    }
}
