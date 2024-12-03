package com.burglak.linker.activity;

import com.burglak.linker.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartupActivityReset {

    private UserRepository userRepository;

    public StartupActivityReset(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void resetUserActitvityStatus() {
        userRepository.updateAllUsersToInactive();
    }
}
