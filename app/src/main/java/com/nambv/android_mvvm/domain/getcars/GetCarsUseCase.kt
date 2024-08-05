//package com.nambv.android_mvvm.domain.getcars
//
//import com.nambv.android_mvvm.data.model.CarSearchResponseItem
//import com.nambv.android_mvvm.data.repository.UsersRepository
//import com.nambv.android_mvvm.domain.util.CarOrder
//import com.nambv.android_mvvm.domain.util.OrderType
//import com.nambv.data.common.Resource
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.flow.*
//import javax.inject.Inject
//
//class GetCarsUseCase @Inject constructor(
//    private val repository: UsersRepository,
//    private val defaultDispatcher: CoroutineDispatcher
//) {
//
//    private lateinit var carList: List<CarSearchResponseItem>
//
//    suspend operator fun invoke(): Flow<Resource<List<CarSearchResponseItem>>> =
//        flow<Resource<List<CarSearchResponseItem>>> {
//            try {
//                emit(Resource.loading())
//                carList = repository.getUsers()
//                emit(Resource.success(carList))
//            } catch (e: Throwable) {
//                emit(Resource.error(e))
//            }
//        }.flowOn(defaultDispatcher)
//
//    suspend operator fun invoke(
//        query: String,
//        carOrder: CarOrder = CarOrder.Date(OrderType.Descending)
//    ): Flow<List<CarSearchResponseItem>> = flow {
//        filter(flowOf(carList), query, carOrder).collect { emit(it) }
//    }
//
//    private fun filter(
//        response: Flow<List<CarSearchResponseItem>>,
//        query: String,
//        carOrder: CarOrder = CarOrder.Date(OrderType.Descending)
//    ): Flow<List<CarSearchResponseItem>> {
//        val filter = response.map { it.filter { car -> car.make.lowercase().contains(query) } }
//        return filter.map { cars ->
//            when (carOrder.orderType) {
//                is OrderType.Ascending -> {
//                    when (carOrder) {
//                        is CarOrder.Title -> cars.sortedBy { it.model.lowercase() }
//                        is CarOrder.Date -> cars.sortedBy { it.firstRegistration }
//                        is CarOrder.Color -> cars.sortedBy { it.colour }
//                        is CarOrder.Price -> cars.sortedBy { it.price }
//                    }
//                }
//
//                is OrderType.Descending -> {
//                    when (carOrder) {
//                        is CarOrder.Title -> cars.sortedByDescending { it.model.lowercase() }
//                        is CarOrder.Date -> cars.sortedByDescending { it.firstRegistration }
//                        is CarOrder.Color -> cars.sortedByDescending { it.colour }
//                        is CarOrder.Price -> cars.sortedByDescending { it.price }
//                    }
//                }
//            }
//        }.flowOn(defaultDispatcher)
//    }
//}
//
