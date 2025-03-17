package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    //회원 가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        // null값을 반환할 수도 있는 경우 Optional로 감싸서 사용하는데 이 Optional안에 여러가지 기능을 제공해준다
        // 그 중 하나가 ifPresent인데 이건 result에 값이 있는 경우 저렇게 예외를 던져주는 코드라 보면 될 거 같다.

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); // 중복 회원 검증

            repository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }


    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
         .ifPresent(m -> {
             throw new IllegalStateException("이미 존재하는 회원입니다.");
         });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }
}
