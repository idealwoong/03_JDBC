package com.ohgiraffers.understand;

import com.ohgiraffers.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        EmployeeDTO selectEmp = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("q1"));

            rset = pstmt.executeQuery();

            while (rset.next()) {

                selectEmp = new EmployeeDTO();

                selectEmp.setEmpId(rset.getString("emp_id"));
                selectEmp.setEmpName(rset.getString("emp_name"));
                selectEmp.setEmpNo(rset.getString("EMP_NO"));
                selectEmp.setEmail(rset.getString("Email"));
                selectEmp.setPhone(rset.getString("PHONE"));
                selectEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectEmp.setJobCode(rset.getString("JOB_CODE"));
                selectEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectEmp.setSalary(rset.getInt("SALARY"));
                selectEmp.setBonus(rset.getDouble("BONUS"));
                selectEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectEmp.setHireDate(rset.getDate("hIRE_dATE"));
                selectEmp.setEntDate(rset.getDate("ENT_DATE"));
                selectEmp.setEntYn(rset.getString("ENT_YN"));
            }


        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            close(con);
            close(pstmt);
            close(rset);

        }
        System.out.println("selectEMP = " + selectEmp);


    }
}
