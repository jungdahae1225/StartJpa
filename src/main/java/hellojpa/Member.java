package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity //JPA가 관리하는 애라는 걸 선언. 이걸 선언 해줘야 JPA가 관리하는 객체가 될 수 있음.
//@Table(name = "DB에는 Member(클래스 이름)과 다르게 테이블 명으로 설정되어 있을 때 이곳에 테이블 이름을 수동으로 작성해주면 됨.")
public class Member {
    @Id
    //현재 DB의 항목들과 맞추어 만들어주면 됨.: 현재 DB에 id와 name컬럼을 만들어 두었으니까.
    private Long id;

    //@Column(name = "컬럼 이름이 name이 아니라 다른 것일 때 여기에 수동으로 써주면 됨.")
    private String name;

    //==DB에 Integer 타입 사용 가능 => 숫자로 인식하여 insert한다.==//
    private Integer age;

    //==DB에는 Enum 타입이 따로 없다. 따라서 annotaion으로 선언 해줘야 한다. => 이를 해석해 varchar로 선언해준다.==//
    @Enumerated(EnumType.STRING)
    /*
        주의!! ==> EnumType이 ORDINAL도 있는데, Enum의 타입 이름들이 그대로 들어가는 STRING타입과 달리
        ORDINAL는 DB에서 임시의 번호를 매겨 DB에 저장한다. 만일 나중에 enum 타입이 수정된다면 타입별 숫자가 중복 되는 버그가 될 수 있으므로
        쓰지 않는 것이 좋다.
    */
    private RoleType roleType;

    //==시간 관련 -- 사실 요즘은 JAVA 8버전의 localdate와 localdatetime을 쓰게 되므로 요즘은 딱히 필요 없는 기능이기도 하다.==//
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //==최신 스타일// JAVA8의 속성을 이용한 날짜 타입 생성==//
    private LocalDate time1;
    private LocalDateTime time2;

    //==varchar를 넘어서는 큰 단위를 쓰고 싶을 때 문자 타입은 CLob으로 생성. 나머지는 BLob==//
    @Lob
    private String description;

    //==코드에는 쓰고 싶은데 DB에 반영하고 싶지 않을 때==//
    @Transient
    private int temp;

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
