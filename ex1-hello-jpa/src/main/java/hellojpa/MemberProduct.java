package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;
}
