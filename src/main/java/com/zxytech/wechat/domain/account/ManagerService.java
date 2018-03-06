package com.zxytech.wechat.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author xwxia
 * @date 2018/3/6 11:13
 */
public class ManagerService implements UserDetailsService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerRepository.findByUsernameAndActiveTrue(username);
        if (null != manager) {
            manager.setLastLoginAt(System.currentTimeMillis());
            managerRepository.save(manager);
            return manager;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
