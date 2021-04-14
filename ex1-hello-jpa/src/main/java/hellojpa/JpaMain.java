package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Team team = new Team();
            team.setName("teamA");
            member1.setTeam(team);
            em.persist(team);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());

            System.out.println("m = " + m.getTeam().getClass());

            System.out.println("=================");
            m.getTeam().getName(); // 초기화
            System.out.println("=================");


//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("reference = " + findMember.getClass()); // Member

////            Member findMember = em.find(Member.class, member.getId());
//
//            Member findMember = em.getReference(Member.class, member.getId());
//
////            실제 객체가 없는 상태
//            System.out.println("before findMember = " + findMember.getClass());
//            System.out.println("findMember.username = " + findMember.getUsername());
//
////            프록시가 객체를 받아옴
//            System.out.println("before findMember = " + findMember.getClass());
//            System.out.println("findMember.username = " + findMember.getUsername());



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
