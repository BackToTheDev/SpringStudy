package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long Id); //Optional은 null을 그대로 반환하기 보단 Optional에 감싸서 반환하는 것이 요즘 코딩법

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
