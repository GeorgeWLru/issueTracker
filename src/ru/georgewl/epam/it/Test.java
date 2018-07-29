package ru.georgewl.epam.it;

import com.alibaba.fastjson.JSON;
import java.util.List;
import ru.georgewl.epam.it.persistence.DBUtilUnknownTypeException;
import ru.georgewl.epam.it.persistence.PersistenceHelper;
import ru.georgewl.epam.it.persistence.Reference;

/**
 *
 * @author Yury Belozyorov, PTS
 */
public class Test {
    
    public static User test2() throws Exception {
        PersistenceHelper.getInstance().start(new Model(), "D:/PROJECTS/tomcat_root/webapps/issueTracker/test");
        
        User u= PersistenceHelper.getInstance().retrieve(User.class, 2);
        String s= u.getFirstName();
        System.out.println(s);
        
        u.setFirstName("Bill");
        u.setUsername("userB");
        
        PersistenceHelper.getInstance().update(u);
        
        User uDelete= new User();
        uDelete.setId(3);
        PersistenceHelper.getInstance().delete(uDelete);
        
        PersistenceHelper.getInstance().stop();
        
        return u;
    }
    
    public static void test3() throws Exception {
        PersistenceHelper.getInstance().start(new Model(), "D:/PROJECTS/tomcat_root/webapps/issueTracker/test2");
        PersistenceHelper.getInstance().stop();
    }
    
    public static User test4() throws Exception {
        PersistenceHelper.getInstance().start(new Model(), "D:/PROJECTS/tomcat_root/webapps/issueTracker/test");
        
        User u= new User();
        u.setId(18);
        u.setUsername("userD");
        u.setFirstName("Danny");
        
        PersistenceHelper.getInstance().persist(u);
        
        PersistenceHelper.getInstance().stop();
        
        return u;
    }
    
    public static void test5() throws Exception {
        PersistenceHelper.getInstance().start(new Model(), "D:/PROJECTS/tomcat_root/webapps/issueTracker/test");
        
        List<User> list= PersistenceHelper.getInstance().selectAll(User.class);
        System.out.println(list.size());
        
        PersistenceHelper.getInstance().stop();
    }
    
    public static void testOps() throws Exception {
        PersistenceHelper.getInstance().start(new Model(), "D:/PROJECTS/tomcat_root/webapps/issueTracker/test");
        
        List<User> list= PersistenceHelper.getInstance().selectAll(User.class);
        
        User u= new User();
        u.setUsername("userK");
        u.setFirstName("Klint");
        u.setLastName("Karrot");
        PersistenceHelper.getInstance().persist(u);
        long idK= u.getId();
        
        u.setUsername("userL");
        u.setFirstName("Lindon");
        u.setLastName("Lendsson");
        PersistenceHelper.getInstance().persist(u);
        long idL= u.getId();
        
        list= PersistenceHelper.getInstance().selectAll(User.class);

        User v= PersistenceHelper.getInstance().retrieve(User.class, idK);
        PersistenceHelper.getInstance().delete(v);
        
        u.setUsername("userM");
        u.setFirstName("Martin");
        PersistenceHelper.getInstance().update(u);
        
        list= PersistenceHelper.getInstance().selectAll(User.class);

        PersistenceHelper.getInstance().delete(u);
        list= PersistenceHelper.getInstance().selectAll(User.class);
        
        PersistenceHelper.getInstance().stop();
    }
    
    public static void test6() throws DBUtilUnknownTypeException {
//        String s= DBUtil.getSQLType(int.class);
//        System.out.println(s);
    }
    
    public static void test7() throws Exception {
        PersistenceHelper.getInstance().start(new Model(), "D:/PROJECTS/tomcat_root/webapps/issueTracker/test");
        
        Issue i= PersistenceHelper.getInstance().retrieve(Issue.class, 137);
        List<Issue> list= PersistenceHelper.getInstance().selectAll(Issue.class);
        
        PersistenceHelper.getInstance().stop();
    }
    
    public static void test8() throws Exception {
        PersistenceHelper.getInstance().start(new Model(), "D:/PROJECTS/tomcat_root/webapps/issueTracker/test");
        
        Project p= PersistenceHelper.getInstance().retrieve(Project.class, 1);
        Reference pr= PersistenceHelper.getInstance().makeReference(p);
        User u= PersistenceHelper.getInstance().retrieve(User.class, 2);
        Reference ur= PersistenceHelper.getInstance().makeReference(u);
        
        Issue i= new Issue();
        i.setTopic("issue");
        i.setType("issue");
        i.setPriority(8);
        i.setAssigneeRef(ur);
        i.setProjectRef(pr);
        i.setDescription("Again!");
        
        PersistenceHelper.getInstance().save(i);
        
        PersistenceHelper.getInstance().stop();
    }
    
    public static void test9() throws Exception {
        PersistenceHelper.getInstance().start(new Model(), "D:/PROJECTS/tomcat_root/webapps/issueTracker/test");
        
        User u= PersistenceHelper.getInstance().retrieve(User.class, 6);
        Reference ur= PersistenceHelper.getInstance().makeReference(u);
        
        List<Issue> list= PersistenceHelper.getInstance().selectFilteredByReference(Issue.class, "ASSIGNEE_USER_ID", ur);
        
        PersistenceHelper.getInstance().stop();
    }
    
    public static void test10() throws Exception {
        Issue n= JSON.parseObject("{\"description\":\"Some bug occure\",\"id\":140,\"priority\":10,\"topic\":\"one more\",\"type\":\"bug\"}", Issue.class);
        System.out.println(n.getTopic());
    }
    
    
    public static void main(String[] args) throws Exception {
        test10();
    }
    
}
