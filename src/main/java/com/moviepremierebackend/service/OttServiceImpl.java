package com.moviepremierebackend.service;

import org.springframework.stereotype.Service;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.Ott;
import com.moviepremierebackend.repository.OttRepository;

@Service
public class OttServiceImpl implements OttService {
	
	private final OttRepository ottRepository;
	
	public OttServiceImpl (OttRepository ottRepository) {
		this.ottRepository = ottRepository;
	}
	@Override
	public void saveOtt(Ott ott) {
		// TODO Auto-generated method stub
		if(!ottExist(ott)) {
			this.ottRepository.save(ott);
		}
		

	}
	@Override
	public Ott findByOttName(String ottName) {
		// TODO Auto-generated method stub
		return this.findByOttName(ottName);
	}
	@Override
	public Boolean ottExist(Ott ott) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	  public void setOttToMovie(Movie movie) {
        Ott ott = movie.getOtt();
        Ott existingOtt = findByOttName(ott.getOttName());

        if (existingOtt != null) {
            movie.setOtt(existingOtt);
        } else {
            saveOtt(ott);
        }
    }

}
