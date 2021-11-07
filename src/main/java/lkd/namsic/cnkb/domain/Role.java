package lkd.namsic.cnkb.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
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