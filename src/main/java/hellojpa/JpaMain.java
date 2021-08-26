package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {

    public static void main(String[] args) {
        /**JPA는 하나의 DB당 하나의 EntityManagerFactory를 만들어야 한다.**/
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        /**고객의 요청이 올 때마다 EntityManager를 통해서 작업을 해야한다.**/
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //code

        /**==jpa을 통한 모든 작업은 꼭 트랜젝션 단위로 해당 트랜젝션 안에서 작업해야 하기 때문에 작업을 하나의 트랜젝션으로 감싸주어야 한다.
         * 즉, JPA의 모든 데이터 변경은 트랜젝션 안에서 실행해야한다.==**/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); //트랜젝션 시작

        try {
            //==새로운 맴버를 DB에 저장 할 거임==//
            Member member = new Member();

            //==member객체 값 세팅 - 이렇게 셋팅만 해주면 jpa에서 알아서 쿼리 날려서.==//
            member.setId(1L);
            member.setName("HelloA");

            /** 탐색
                //==DB에 저장된 정보 탐색(찾기). 찾고 -> remove ==//
                Member findMember = entityManager.find(Member.class,1L); //첫번째 인자 = 엔티티 클래스 & 두번째인자 = 기본키
                findMember.getId(); //인자로 넘겨준 기본키를 바탕으로 ID를 찾아줄거임.
                findMember.getName();

                entityManager.remove(findMember); //찾은 애 삭제하는 쿼리
             **/

            /**
             * Member findMember = entityManager.find(Member.class,1L);
             * findMember.setName("수정할 이름"); (JPA는 JAVA 컬렉션을 쓰는 것 같은 효과를 얻게 해준다.)
             *
             * entityManager.persist(member); <=이거 다시 안해줘도 됨!! 위의 두줄로 수정 끝!
             * (이미 한 번 jpa에 저장이 됐으면 jpa가 이미 저장해서 가지고 있기 때문에 알아서 수정 업데이트 해준다.)
             */

            //==jpa의 .persist()문법을 써서 영속 시켜주면 끝.==//
            entityManager.persist(member);
            //entityManager.detach(member); 준영속성 상태로 만들기

            /**==jpa 저장 행위가 끝난 후 트랜젝션 커밋으로 트랜젝션 단위 끝내기.
             * 변경을 했다면 꼭 트랜젝션 커밋 해야한다. -> 그래야 영속성 컨텍스트_쓰기 지연 SQL 저장소 안에 있던 DB에 쿼리가 날라가게 됨(JPA용어로 이를 flush라고 한다.) ==**/
            transaction.commit();
        }catch (Exception e){
            //문제가 발생 하면 catch문에서 예외처리로 트랜젝션 롤백해주기.
            transaction.rollback();
        }finally {
            //다 쓰면 닫아줘야 함.
            entityManager.close();
        }
        //전체가 끝나면 다 쓰면 entityManagerFactory 닫아줘야 함.
        entityManagerFactory.close(); //커넥션 풀링을 위해.

    }
}
