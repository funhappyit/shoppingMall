package jpashop.service;

import jpashop.domain.Member;
import jpashop.repository.MemberRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public class MemberServiceTest{
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        // //Given
        // Member member = new Member();
        // member.setName("kim");
        //
        //
        // //When
        // Long saveId = memberService.join(member);
        // //Then
        // assertEquals(member, memberRepository.findOne(saveId));
    }

    // @Test(expected = IllegalStateException.class)
    // public void 중복_회원_예외() throws Exception{
    //     //Given
    //     Member member1 = new Member();
    //     member1.setName("kim");
    //
    //     Member member2 = new Member();
    //     member2.setName("kim");
    //
    //     //When
    //     memberService.join(member1);
    //     memberService.join(member2);
    //
    //     fail("예외가 발생해야 한다.");
    //
    // }

}