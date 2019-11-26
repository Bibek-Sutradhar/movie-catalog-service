package com.example.moviecatalogservice.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.moviecatalogservice.model.CatalogItem;
import com.example.moviecatalogservice.model.MovieInfo;
import com.example.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClient;
	
	@GetMapping("/all")
	public List<CatalogItem> getAllMovies() {
		
		List<CatalogItem> movieList = Collections.singletonList(new CatalogItem(1,"Movie 1", "Movie 1", "4"));
		return movieList;
	}
	
	@GetMapping("/{movieId}")
	public List<CatalogItem> getMovieDetailsById(@PathVariable ("movieId") int id) {
		//get all the rated movies
		List<Rating> rating = Arrays.asList(new Rating(1,"4"),
											new Rating(2,"3"),
											new Rating (3,"2"));
		
		//Lambda function to fetch movie info for all the rated movies
		return rating.stream().map(rat ->{
		MovieInfo movieInfo = restTemplate.getForObject("http://localhost:8081/movieinfo/" + rat.getMovieId(), MovieInfo.class);
			return new CatalogItem(movieInfo.getMovieId(), movieInfo.getName(), "DESC",rat.getRating());					
		})
		.collect(Collectors.toList());
		
		//Now using WebClient.Builder to make a call to another microservice
		/*return rating.stream().map(rat->{
		MovieInfo movie = webClient.build()
						.get()
						.uri("http://localhost:8081/movieinfo/" + rat.getMovieId())
						.retrieve()
						.bodyToMono(MovieInfo.class)
						.block();
		return new CatalogItem(movie.getMovieId(), movie.getName(), "desc", rat.getRating());
		}).collect(Collectors.toList());*/
		
		/*List<MovieInfo> movieInfoList = new ArrayList<MovieInfo>();
		//List<CatalogItem> catalogItems = new ArrayList<>();
		//get the movie info details for the movieid
		//call the movie info service via RESTTeamplate
		Iterator<Rating> ratingItr = rating.iterator();
		
		while(ratingItr.hasNext()) {
			Rating rat = ratingItr.next();
			
			MovieInfo movieInf = restTemplate.getForObject("http://localhost:8081/movieinfo/" + rat.getMovieId(), MovieInfo.class);
			//CatalogItem catalogItem = new CatalogItem(rat.getMovieId(), movieInf.getName(), movieInf.getName(), rat.getRating());
			movieInfoList.add(movieInf);
			//catalogItems.add(catalogItem);
		}
		
		return movieInfoList;*/
	}
}
