//
// DetailView.swift
// Cityflo
//
// Created by Abhishek Agarwal on 20/08/23.
// Copyright © Cityflo. All rights reserved.
//


import SwiftUI
import shared

struct DetailScreen: View {
    let movie: Movie
    
    var body: some View {
        ScrollView{
            VStack{
                AsyncImage(url: URL(string: movie.posterImage)){image in
                    image.resizable().scaledToFill()
                }placeholder: {
                    ProgressView()
                }
                .frame(maxWidth: .infinity, minHeight: 300, maxHeight: 300)
                
                VStack(alignment:.leading, spacing: 12){
                    Text(movie.title)
                        .font(.title)
                        .fontWeight(.bold)
                        .fixedSize(horizontal: false, vertical: true)
                    
                    Button(action: {}){
                        HStack(spacing : 12){
                            Image(systemName: "play.fill")
                                .foregroundColor(.white)
                            
                            Text("Start watching now")
                                .foregroundColor(.white)
                        }
                    }
                    .frame(maxWidth: .infinity, maxHeight: 40)
                    .padding()
                    .background(.red)
                    .clipShape(RoundedRectangle(cornerRadius: 8))
                    
                    Text("Released in \(movie.releaseDate)".uppercased())
                    
                    Text(movie.overview)
                        .font(.body)
                        .fixedSize(horizontal: false, vertical: true)
                    
                }
                .padding(20)
                .background(.black)
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            
        }
    }
}
