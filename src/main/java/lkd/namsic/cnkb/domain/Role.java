package lkd.namsic.cnkb.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Column(length = 15, nullable = false, unique = true)
    String name;
    
    @Builder.Default
    @OneToMany(mappedBy = "pk.role", cascade = CascadeType.ALL)
    List<UserRole> userRoleList = new ArrayList<>();
    
}