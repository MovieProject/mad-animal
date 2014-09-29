package movie.unitTest;

import static org.junit.Assert.*;
import movie.business.dao.MemberDao;
import movie.business.domain.Member;
import movie.dataaccess.MemberDaoImpl;

import org.junit.Ignore;
import org.junit.Test;

public class MemberDaoUnitTest {
	MemberDao memberAccess =new MemberDaoImpl();
	Member member = new Member("iu","iu","아이유",23,"방배동","iu@iu.net","010-2345-7987",0);
	String memberID = "iu";
	@Test
	public final void testInsertMember() {
		memberAccess.insertMember(member);
		assertEquals(true, memberAccess.memberIDExists(member.getMemberID()));
	}

	@Test
	@Ignore
	public final void testMemberDaoImpl() {
	}

	@Test
	@Ignore
	public final void testSelectMemberList() {
	}

	@Test
	public final void testSelectMemberCount() {
		assertEquals("숫자 받아오기",1, memberAccess.selectMemberCount());
	}


	@Test
	public final void testUpdateMember() {
		member.setMemberName("이지은");
		memberAccess.updateMember(member);
		
		System.out.println(memberAccess.selectMember("iu"));
		System.out.println(memberAccess.memberIDExists("iu"));
		
		assertTrue(memberAccess.memberIDExists("iu"));
	}

	@Test
	public final void testDeleteMember() {
		memberAccess.deleteMember(memberID);
		assertEquals(false, memberAccess.memberIDExists(memberID));
	}

	@Test
	public final void testMemberIDExists() {
		assertTrue("존재하냐??", memberAccess.memberIDExists("admin"));
	}

	@Test
	public final void testSelectMember() {
	assertEquals("선택된거냐??",true, memberAccess.selectMember("admin").getMemberID().equals("admin"));
	}
}
