package ru.otus.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author sergey
 * created on 02.10.18.
 */
class BatisDemo {
    private final SqlSessionFactory sqlSessionFactory;

    BatisDemo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
    }

    void start() {
        selectOne();
//        selectAll();
//        selectLike();
//        selectForEach();
//        insert();
//        selectOneInterface();
//        selectCached();
    }

    private void selectOne() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Test test = session.selectOne("testMapper.selectTestOne", 1);
            System.out.println("selected: " + test);
        }
    }

    private void selectAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map<String, Integer> params = new HashMap<>();
            params.put("minId", 50);
            params.put("maxId", 55);
            List<Test> testList = session.selectList("testMapper.selectTestAll", params);
            System.out.println("selected: " + testList);
        }
    }

    private void selectLike() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map<String, String> params = new HashMap<>();
            params.put("nameParam", "%2%");
            List<Test> testList = session.selectList("testMapper.selectTestLike", params);
            System.out.println("selected like with nameParam: " + testList);

            testList = session.selectList("testMapper.selectTestLike");
            System.out.println("selected like without nameParam: " + testList);
        }
    }

    private void selectForEach() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Integer> params = Arrays.asList(1,2,3,4,5);
            List<Test> testList = session.selectList("testMapper.selectTestForEach", params);
            System.out.println("selectedForEach: " + testList);
        }
    }

    private void insert() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map<String, String> params = new HashMap<>();
            params.put("id", "500");
            params.put("name", "TestInsertovich");
            int rowCount = session.insert("testMapper.insert", params);
            System.out.println("inserted: " + rowCount);

            Test test = session.selectOne("testMapper.selectTestOne", 500);
            System.out.println("selected: " + test);
        }
    }

    //Optional добавили в 3.5.0 (только для "интерфейсов")
    private void selectOneInterface() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapperInterface mapper = session.getMapper(TestMapperInterface.class);
            Optional<Test> test = mapper.findOne(1);
            System.out.println("selected: " + test);

            Optional<Test> testNotExists = mapper.findOne(-1);
            System.out.println("selected: " + testNotExists);

        }
    }

    private void selectCached() {
        try (SqlSession session = sqlSessionFactory.openSession()) {

            Test test = session.selectOne("testMapper.selectTestOne", 1);
            System.out.println("1 selected: " + test);

            session.selectOne("testMapper.selectTestOne", 1);
            System.out.println("2 selected: " + test);

            session.selectOne("testMapper.selectTestOne", 1);
            System.out.println("3 selected: " + test);

            session.selectOne("testMapper.selectTestOne", 4);
            System.out.println("4 selected: " + test);

        }
    }


}
