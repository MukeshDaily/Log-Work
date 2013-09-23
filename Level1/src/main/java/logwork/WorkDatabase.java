package logwork;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class WorkDatabase {
    public WorkDatabase(){
	    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:mem");
        dataSource.setPassword("");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);      
	}
	
    public makeTable(){
	    System.out.println("Creating tables");
        jdbcTemplate.execute("drop table worklogs if exists");
        jdbcTemplate.execute("create table worklogs(" +
                "id serial, work_title varchar(255), work_content varchar(255))");
	}
	
	public insertData(Work work){	    		
            System.out.printf("Inserting worklogs record for %s %s\n", work.getTitle(), work.getContent());
            jdbcTemplate.update(
                    "INSERT INTO worklogs(work_title,work_content) values(?,?)",
                    work.getTitle(), work.getContent());        
	}
	
	public List<DataHolder> retrieveData(){		
        List<DataHolder> results = jdbcTemplate.query(
                "select * from worklogs",
                new RowMapper<DataHolder>() {
                    @Override
                    public DataHolder mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new DataHolder(rs.getLong("id"), rs.getString("work_title"),
                                rs.getString("work_content"));
                    }
                });
		return results;
	}
	
	public printData(){
		System.out.println("Querying for worklogs records:");
        List<DataHolder> results = jdbcTemplate.query(
                "select * from worklogs",
                new RowMapper<DataHolder>() {
                    @Override
                    public DataHolder mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new DataHolder(rs.getLong("id"), rs.getString("work_title"),
                                rs.getString("work_content"));
                    }
                });

        for (DataHolder dh : results) {
            System.out.println(dh);
        }
	
	}
}
