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

            Member member2 = new Member();
            member1.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());

            System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass()));


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
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
