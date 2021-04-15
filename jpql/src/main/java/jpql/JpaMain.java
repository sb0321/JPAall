package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.changeTeam(team);
            member1.setAge(20);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.changeTeam(team);
            member2.setAge(10);

            em.persist(member1);
            em.persist(member2);


            em.flush();
            em.clear();

//            String query = "select concat('a', 'b') from Member m";
//            String query = "select substring(m.username, 2, 3) from Member m";
//            String query = "select locate('de', 'abcdegf') from Member m";
//            String query = "select size(t.members) from Team t";
//            String query = "select group_concat(m.username) from Member m";


            String query = "select m.username from Team t join t.members m";

            List<String> result = em.createQuery(query, String.class).getResultList();

            System.out.println("result = " + result);
            
//            String query = "select nullif(m.username, '관리자') from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

//            String query =
//                    "select " +
//                        "case when m.age <= 10 then '학생요금' " +
//                        "     when m.age >= 60 then '경로요금' " +
//                        "     else '일반요금' " +
//                        "end " +
//                    "from Member m";
//
//            List<String> result = em.createQuery(query, String.class).getResultList();
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }


//            String query = "select m.username , 'HELLO', TRUE from Member m where m.type = :userType";
//            List<Object[]> result = em.createQuery(query)
//                    .setParameter("userType", MemberType.ADMIN)
//                    .getResultList();

//            for (Object[] objects : result) {
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }
            
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
