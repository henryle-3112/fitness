package henry.greenwich.fitness.controller.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.user.Role;
import henry.greenwich.fitness.service.user.RoleService;

import java.util.List;

@Controller
public class RoleController {
    /**
     * roleService - roleService to interact with role's data
     */
    private RoleService roleService;

    /**
     *
     * @param roleService - inject roleService
     */
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     *
     * @return list of roles
     */
    @GetMapping(value = "/roles", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Role> getRoles() {
        return this.roleService.getRoles();
    }

    /**
     *
     * @param id - role's id that user want to get
     * @return role by id
     */
    @GetMapping(value = "/roles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Role getRole(@PathVariable Long id) {
        return this.roleService.getRole(id);
    }

    /**
     *
     * @param role - role that user want to add
     * @return role that was inserted to the database
     */
    @PostMapping(value = "/roles/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Role addRole(@RequestBody Role role) {
        return this.roleService.addRole(role);
    }

    /**
     *
     * @param role - role that user want to update
     * @return role - role that was updated successfully
     */
    @PostMapping(value = "/roles/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Role updateRole(@RequestBody Role role) {
        return this.roleService.updateRole(role);
    }

    /**
     *
     * @param id - role's id that user want to delete
     */
    @PostMapping(value = "/roles/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteRole(@PathVariable Long id) {
        this.roleService.deleteRole(id);
    }
}
