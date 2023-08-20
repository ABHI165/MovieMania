//
// HomeVIewModel.swift
// Cityflo
//
// Created by Abhishek Agarwal on 20/08/23.
// Copyright © Cityflo. All rights reserved.
//


import Foundation
import SwiftUI
import shared

struct MovieState {
    let movies: [Movie]
    let isLoading: Bool
    let isRefreshing: Bool
    let errorMessage: String?
}

@MainActor
class HomeViewModel: ObservableObject {
    private let getPopularMovieUseCase: GetPopularMovieUseCase
    private var currentPage = 0
    @Published private(set) var uiState: MovieState
    
    
    init(getPopularMovieUseCase: GetPopularMovieUseCase = GetPopularMovieUseCase.init()) {
        self.getPopularMovieUseCase = getPopularMovieUseCase
        uiState = MovieState(movies: [],
                             isLoading: false,
                             isRefreshing: false,
                             errorMessage: nil)
    }
    
    
    func loadMovies(isForceReload: Bool = false) async {
        uiState = MovieState(movies: uiState.movies,
                             isLoading: !isForceReload,
                             isRefreshing: isForceReload,
                             errorMessage: nil)
        currentPage = isForceReload ? 1 : currentPage + 1
        
        do {
            let movies = try await getPopularMovieUseCase.invoke(page: Int32(currentPage))
            uiState = MovieState(movies: isForceReload ? movies : uiState.movies + movies,
                                 isLoading: false,
                                 isRefreshing: false,
                                 errorMessage: nil)
        } catch {
            uiState = MovieState(movies: uiState.movies,
                                 isLoading: false,
                                 isRefreshing: false,
                                 errorMessage: "Something Went Wrong!")
        }
    }
}
