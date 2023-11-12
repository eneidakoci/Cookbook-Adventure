//package com.cookbook.service;
//
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CookbookAdventureUserAuthenticationProvider implements UserAuthenticationProvider {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public CookbookAdventureUserAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication)
//            throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        UserEntity user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new AuthenticationServiceException("Invalid username");
//        }
//
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new AuthenticationServiceException("Invalid password");
//        }
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        switch (user.getRole()) {
//            case ADMIN:
//                authorities.add(new GrantedAuthority() {
//                    @Override
//
//
//                    public String getAuthority() {
//                        return
//
//                                "ROLE_ADMIN";
//                    }
//                });
//
//                authorities.add(new GrantedAuthority() {
//                    @Override
//
//
//                    public String getAuthority() {
//                        return
//
//                                "ROLE_USER";
//                    }
//                });
//                break;
//            case USER:
//                authorities.add(new GrantedAuthority() {
//                    @Override
//                    public String getAuthority() {
//                        return "ROLE_USER";
//                    }
//                });
//                break;
//            case UNAUTHORIZED:
//                break;
//            default:
//                break;
//        }
//
//        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
//
//        return newAuthentication;
//    }
//
//    @Override
//
//
//    public boolean
//
//    supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}