package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Role;

public interface RoleImpl {
    Role findPageRoleForDeveloper(int developerId, int pageId);

    void assignWebsiteRole(int developerId, int websiteId, String role);

    void assignPageRole(int developerId, int pageId, String role);

    void deleteWebsiteRole(int developerId, int websiteId, String role);

    void deletePageRole(int developerId, int pageId, String role);
}
