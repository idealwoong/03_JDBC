package com.ohgiraffers.section01;

import com.ohgiraffers.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application04 {
    public static void main(String[] args) {


        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        EmployeeDTO selectEmp = null;

    Scanner scr = new Scanner(System.in);
        System.out.println("조회 사번");
        String empId = scr.nextLine();

        String query = "select * from employee where emp_id = " + empId;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()) {
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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
close(con);
close(stmt);
close(rset);
    }

        System.out.println("selectEMP = " + selectEmp);
    }}
