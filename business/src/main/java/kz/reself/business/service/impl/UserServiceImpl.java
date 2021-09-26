package kz.reself.business.service.impl;

import kz.reself.business.service.IUserService;
import kz.reself.dbstruct.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public List<Users> getUserDef() {
        List<Users> users = new ArrayList<>();
        Users user = restTemplate.getForObject("http://localhost:8077/user/" + 1, Users.class);
        users.add(user);
        return users;
    }
}
