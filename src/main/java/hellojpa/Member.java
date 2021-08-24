package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //JPA가 관리하는 애라는 걸 알려주기 위함
//@Table(name = "DB테이블에는 Member(클래스 이름)과 다르게 설정되어 있을 때 이곳에 테이블 이름을 수동으로 작성해주면 됨.")
public class Member {
    @Id
    //현재 DB의 항목들과 맞추어 만들어주면 됨.: 현재 DB에 id와 name컬럼을 만들어 두었으니까.
    private Long id;

    //@Column(name = "컬럼 이름이 name이 아니라 다른 것일 때 여기에 수동으로 써주면 됨.")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
