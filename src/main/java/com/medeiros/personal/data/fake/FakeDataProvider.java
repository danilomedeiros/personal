package com.medeiros.personal.data.fake;

import java.util.ArrayList;
import java.util.Collection;

import com.medeiros.personal.data.DataProvider;
import com.medeiros.personal.domain.Aluno;

public class FakeDataProvider implements DataProvider{

	Collection<Aluno> alunos;
	
	@Override
	public Collection<Aluno> getAlunos() {
		if(alunos == null){
			popAlunos();
		}
		return alunos;
	}

	private void popAlunos() {
		alunos = new ArrayList<Aluno>();
		Aluno a = new Aluno();
		a.setFirstName("Antônio");
		a.setLastName("Alves");
		a.setEmail("antoniovei@hotmail.com");
		a.setPhone("(86) 99949-9949");
		a.setMale(true);
		a.setBio("Isso é uma bio");
		alunos.add(a);
		
		a = new Aluno();
		a.setFirstName("Adriana");
		a.setLastName("Alves");
		a.setEmail("adriana@hotmail.com");
		a.setPhone("(86) 99543-9660");
		a.setMale(false);
		a.setBio("Isso é uma bio 2");
		alunos.add(a);

		a = new Aluno();
		a.setFirstName("Rayssa");
		a.setLastName("Junqueira");
		a.setEmail("rayssa@yahoo.com");
		a.setPhone("(86) 99323-1232");
		a.setMale(false);
		a.setBio("Isso é uma bio 3");
		alunos.add(a);

		a = new Aluno();
		a.setFirstName("Jane");
		a.setLastName("Silva");
		a.setEmail("jane@terra.com");
		a.setPhone("(86) 9876-2345");
		a.setMale(false);
		a.setBio("Isso é uma bio 4");
		alunos.add(a);

		a = new Aluno();
		a.setFirstName("Alersson");
		a.setLastName("Cabral");
		a.setEmail("alersson@uol.com");
		a.setPhone("(86) 9384-5432");
		a.setMale(true);
		a.setBio("Isso é uma bio 5");
		alunos.add(a);
	
	}

	@Override
	public int getUnreadNotificationsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
