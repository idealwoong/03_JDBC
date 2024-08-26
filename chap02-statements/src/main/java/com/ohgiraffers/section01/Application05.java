package com.ohgiraffers.section01;

import com.ohgiraffers.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application05 {
    public static void main(String[] args) {


        //여러 DTO를 하나의 list로 묶어서 처리

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        //한 행의 정보를 담을 DTO
        EmployeeDTO row = null;

        // 여러 dTO를 묶을 list
        List<EmployeeDTO> empList = null;

        String query = "select * From Employee";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            empList = new ArrayList<>();

            while (rset.next()) {

                row = new EmployeeDTO();

                row.setEmpId(rset.getString("emp_id"));
                row.setEmpName(rset.getString("emp_name"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("Email"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("hIRE_dATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row);

            }
        } catch(SQLException e){
                throw new RuntimeException(e);

            }finally{
                close(con);
                close(stmt);
                close(rset);

            }
            for (EmployeeDTO emp : empList) {
                System.out.println(emp);
            }

}}

