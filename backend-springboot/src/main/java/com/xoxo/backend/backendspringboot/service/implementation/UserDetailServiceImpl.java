package com.xoxo.backend.backendspringboot.service.implementation;

import com.xoxo.backend.backendspringboot.persistence.entity.Rol;
import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;
import com.xoxo.backend.backendspringboot.persistence.repository.RolRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.UsuarioRepository;
import com.xoxo.backend.backendspringboot.presentation.dto.auth.AuthCreateUserRequest;
import com.xoxo.backend.backendspringboot.presentation.dto.auth.AuthLoginRequest;
import com.xoxo.backend.backendspringboot.presentation.dto.auth.AuthResponse;
import com.xoxo.backend.backendspringboot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuarioEntity = usuarioRepository.findUsuarioByCorreo(correo).orElseThrow(
                () -> new UsernameNotFoundException("El usuario no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuarioEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolEnum().name()))));

        usuarioEntity.getRoles().stream()
                .flatMap(rol -> rol.getListaPermisos().stream())
                .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getNombre())));

        return new User(usuarioEntity.getNombre(),
                usuarioEntity.getContrasenaUsuario(),
                usuarioEntity.isEnabled(),
                usuarioEntity.isAccountNoExpired(),
                usuarioEntity.isCredentialsNoExpired(),
                usuarioEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "Usuario logueado correctamente.", accessToken, true);

        return authResponse;
    }

    public Authentication authenticate(String username ,String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Usuario o contraseña inválida.");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña inválida.");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
        String username = authCreateUserRequest.username();
        String password = authCreateUserRequest.password();
        List<String> roleRequest = authCreateUserRequest.roleRequest().roleListName();
        Set<Rol> roleEntitySet = rolRepository.findRolesByRolEnumIn(roleRequest).stream().collect(Collectors.toSet());

        if (roleEntitySet.isEmpty()) {
            throw new IllegalArgumentException("The roles specified do not exist.");
        }

        Usuario userEntity = Usuario.builder()
                .nombre(username)
                .contrasenaUsuario(passwordEncoder.encode(password))
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialsNoExpired(true)
                .build();

        Usuario userCreated = usuarioRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolEnum().name()))));

        userCreated.getRoles()
                .stream()
                .flatMap(rol -> rol.getListaPermisos().stream())
                .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getNombre())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getNombre(), userCreated.getContrasenaUsuario(), authorityList);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(userCreated.getNombre(), "User created successfully.", accessToken, true);
        return authResponse;
    }

}
