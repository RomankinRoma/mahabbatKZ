package kz.reself.business.service.impl;

import kz.reself.business.repository.RoleRepository;
import kz.reself.business.repository.UserRepository;
import kz.reself.business.service.IUserService;
import kz.reself.dbstruct.model.Roles;
import kz.reself.dbstruct.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users create(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Roles role = this.roleRepository.findById(2L).get();
        List<Roles> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public List<Users> getUserDef() {
        List<Users> users = new ArrayList<>();
        Users user = restTemplate.getForObject("http://crm/user/1", Users.class);
        users.add(user);
        return users;
    }

    @Override
    public String getCheck() {
        String serviceId = "crm";

        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);

        System.out.println("instances = " + instances);
        return "ok";
    }

    @Override
    public String getUserEmail(Long userId) {
        return userRepository.findById(userId).get().getEmail();
    }
}
