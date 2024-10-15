package edu.du.sb1015_2;

import edu.du.sb1015_2.entity.MyData;
import edu.du.sb1015_2.repository.MyDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Sb10152ApplicationTests {

    @Autowired
    MyDataRepository myDataRepository;

    @Test
    void 데이터_모두_가져오기() {
        List<MyData> list = myDataRepository.findAll();
        for (MyData myData : list) {
            System.out.println(myData);
        }
    }

}
