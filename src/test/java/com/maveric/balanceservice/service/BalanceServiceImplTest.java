package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.exception.AccounIdMismatchException;
import com.maveric.balanceservice.exception.NoBalanceFoundException;
import com.maveric.balanceservice.exception.NoBalancesException;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
class BalanceServiceImplTest {
    @Mock
    BalanceRepository repository;
    @InjectMocks
    BalanceServiceImpl service;
    @Mock
    ModelMapper modelMapper;

    public static BalanceDto getBalanceDto(){
        BalanceDto balanceDto=new BalanceDto();
        balanceDto.setAmount(100);
        balanceDto.setCurrency(Currency.INR);
        balanceDto.setAccountId("130");
        balanceDto.setId("1");
        return balanceDto;
    }

    public static Balance getBalance(){
        Balance balance=new Balance();
        balance.setAmount(100);
        balance.setCurrency(Currency.INR);
        balance.setAccountId("130");
        balance.setId("1");
        return balance;
    }

    public static Page<Balance> getBalances(){
        List<Balance> list=new ArrayList<>();
        list.add(getBalance());
        list.add(getBalance());
        list.add(getBalance());
        list.add(getBalance());
        Page<Balance> pages = new PageImpl<Balance>(list);
        return pages;

    }


    @Test
    void should_getpageable_Balances_when_getBalances_invoked_test() throws NoBalancesException {
        when(repository.findAllByAccountId("130")).thenReturn(getBalances().stream().toList());
        when(repository.findAllByAccountId("130",PageRequest.of(0,10))).thenReturn(getBalances());

        Page<Balance> balances=service.getBalances("130",0,10);

        assertNotNull(balances);
        assertEquals(4,balances.getSize());

    }



    @Test
    void should_throwException_Balances_when_getBalances_invoked_test() throws NoBalancesException {
        //when(repository.findAllByAccountId("130")).thenReturn(new ArrayList<>());
        when(repository.findAllByAccountId("130",PageRequest.of(0,10))).thenReturn(Page.empty());


        NoBalancesException exception =
                assertThrows(NoBalancesException.class,
                        ()->{ service.getBalances("130",0,10);
                        },"No Balances in this page, go to the previous page");

        assertEquals("No Balances in this page, go to the previous page",exception.getMessage());

    }
    @Test
    void shoudlupdateBalance_whenInvoked() throws NoBalanceFoundException {
        Balance balance=getBalance();
        when(repository.findById(any(String.class))).thenReturn(Optional.of(balance));
        when(repository.save(any(Balance.class))).thenReturn(balance);

        Balance actualBalance=service.updateBalance("1","1",getBalanceDto());
       assertEquals(balance,actualBalance);
        assertEquals(balance.getId(),actualBalance.getId());

    }
    @Test
    void shoudlthrow_NoBalanceFoundException_updateBalance_whenInvoked() throws NoBalanceFoundException {

        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
      //  when(repository.save(any(Balance.class))).thenReturn(balance);

        NoBalanceFoundException exception=assertThrows(NoBalanceFoundException.class,
                ()->{ service.updateBalance("1","1",getBalanceDto());
            },"No balance found to update for given balance id");
        assertEquals("No balance found to update for given balance id",exception.getMessage());



    }

    @Test
    void deleteBalance_Test() throws AccounIdMismatchException, NoBalanceFoundException {
        when(repository.findById(any(String.class))).thenReturn(Optional.of(getBalance()));
        String msg=service.deleteBalance("130","1");
        assertSame("Balance deleted Successfully",msg);
    }

    @Test
    void deleteBalance_Test_shouldthrow_AccountIdMismatchException() throws AccounIdMismatchException, NoBalanceFoundException {
        when(repository.findById(any(String.class))).thenReturn(Optional.of(getBalance()));

        AccounIdMismatchException ex=assertThrows(AccounIdMismatchException.class,()->{service.deleteBalance("1","1");},"Account ID in url is diff from Account Id of the Actual Account ");
        assertEquals("Account ID in url is diff from Account Id of the Actual Account ",ex.getMessage());
    }

    @Test
    void deleteBalance_Test_shouldthrow_NoBalanceFoundException() throws AccounIdMismatchException, NoBalanceFoundException {
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());

        NoBalanceFoundException ex=assertThrows(NoBalanceFoundException.class,()->{service.deleteBalance("1","1");},"No balance found to delete");
        assertEquals("No balance found to delete",ex.getMessage());
    }
    @Test
    void createBalance() {
        //Arrange
        Balance balance=getBalance();
        when(repository.save(any())).thenReturn(balance);
        //Act
        Balance createdBalance=service.createBalance("130",getBalanceDto());

        //Assert
        assertEquals(balance,createdBalance);
        assertNotNull(createdBalance);
        assertSame(balance.getId(),createdBalance.getId());
    }

    @Test
    void getBalancesByBalanceId() throws NoBalanceFoundException {
        Balance balance=getBalance();
        when(repository.findById(any(String.class))).thenReturn(Optional.of(balance));
        Balance getBalance=service.getBalancesByBalanceId("130","1");

        assertEquals(balance,getBalance);
    }
    @Test
    void getBalancesByBalanceId_throwsNobalanceFound() throws NoBalanceFoundException {
        //Balance balance=getBalance();
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        NoBalanceFoundException ex=assertThrows(NoBalanceFoundException.class,()->{service.getBalancesByBalanceId("1","1");},"No Balances for given balance id1");


        assertEquals("No Balances for given balance id1",ex.getMessage());
    }

}