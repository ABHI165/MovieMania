import SwiftUI
import shared

struct HomeScreenView: View {
    @StateObject var viewModel: HomeViewModel = HomeViewModel()
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)
    
    var body: some View {
        NavigationStack{
            ScrollView{
                LazyVGrid(columns: gridColumns, spacing: 16){
                    
                    ForEach(viewModel.uiState.movies, id: \.id){movie in
                        
                        NavigationLink(value: movie){
                            MovieItem(movie)
                                .task {
                                    if movie == viewModel.uiState.movies.last && !viewModel.uiState.isLoading {
                                        await viewModel.loadMovies()
                                    }
                                }
                        }
                        .buttonStyle(PlainButtonStyle())
                    }
                    
                    if viewModel.uiState.isLoading {
                        Section(footer: ProgressView()){}
                    }
                    
                }
                .padding(.horizontal, 12)
                .navigationDestination(for: Movie.self){movie in
                    DetailScreen(movie: movie)
                }
                
            }
            .navigationTitle("Popular Movies")
            
        }
        .task {
            await viewModel.loadMovies()
        }
    }
    
    @ViewBuilder
    func MovieItem(_ movie: Movie) -> some View {
        VStack(alignment: .leading, spacing: 8){
            ZStack{
                
                AsyncImage(url: URL(string: movie.posterImage)){image in
                    image.resizable()
                }placeholder: {
                    Color.gray
                }
                
                Circle()
                    .frame(width: 50, height: 50)
                    .foregroundColor(.black.opacity(0.7))
                
                Image(systemName: "play.fill")
                
            }
            .frame(maxWidth: .infinity, idealHeight: .infinity)
            .clipShape(RoundedRectangle(cornerRadius: 8))
            
            Text(movie.title)
                .font(.title3)
                .fontWeight(.bold)
                .lineLimit(1)
            
            Text(movie.releaseDate)
                .font(.caption)
        }
        .frame(maxWidth: .infinity, maxHeight: 260)
    }
}

struct HomeScreenView_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreenView()
    }
}
