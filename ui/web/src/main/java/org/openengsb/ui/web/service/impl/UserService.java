/**
 * Copyright 2010 OpenEngSB Division, Vienna University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openengsb.ui.web.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserService implements UserDetailsService {

    private Map<String, UserDetails> data = new HashMap<String, UserDetails>();

    {
        Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new GrantedAuthorityImpl("ROLE_USER"));
        User u1 = new User("test", "password", true, true, true, true, auth);
        data.put("test", u1);
    }

    public UserDetails loadUserByUsername(String username) {
        return data.get(username);
    }

}